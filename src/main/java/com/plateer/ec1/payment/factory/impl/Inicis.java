package com.plateer.ec1.payment.factory.impl;

import com.plateer.ec1.common.code.order.OPT0011Enum;
import com.plateer.ec1.common.model.order.OpPayInfo;
import com.plateer.ec1.common.utils.HttpUtil;
import com.plateer.ec1.payment.enums.BankCode;
import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.factory.Payment;
import com.plateer.ec1.payment.service.PaymentBizService;
import com.plateer.ec1.payment.vo.*;
import com.plateer.ec1.payment.vo.franchisee.FranchiseeReq;
import com.plateer.ec1.payment.vo.inicis.*;
import com.plateer.ec1.payment.vo.member.MemberReq;
import com.plateer.ec1.payment.vo.order.OrderReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class Inicis implements Payment {
    private final PaymentBizService paymentBizService;
    //이니시스 승인 url
    private static final String APPROVE_URL = "https://iniapi.inicis.com/api/v1/formpay";

    //이니시스 가상계좌환불 url
    private static final String REFUND_URL = "https://iniapi.inicis.com/api/v1/refund";

    @Override
    public PaymentType getType() {
        return PaymentType.INICIS;
    }
    /**
     * 이니시스 가상계좌 채번
     * @param payInfo
     * @return
     */
    @Override
    @Transactional
    public ApproveRes approvePay(PayApproveReq payInfo) {
        //가상계좌 채번
        InicisApproveRes inicisApproveRes = inicisApproveCall(InicisApproveReq.of(payInfo));
        //주문결제 insert
        String SUCCESS_CODE = "00";
        if (SUCCESS_CODE.equals(inicisApproveRes.getResultCode())) {
            savePayInfo(payInfo, inicisApproveRes);
        }
        return ApproveRes.of(inicisApproveRes);
    }

    /**
     * 이니시스 입금통보
     * @param req
     */
    @Override
    @Transactional
    public void completePay(PayCompleteReq req) {
        OpPayInfo getPayInfo = paymentBizService.getPayInfo(req.getTrsnId());
        paymentBizService.modifyPayInfo(OpPayInfo.completeOf(getPayInfo));
    }

    /**
     * 결제취소데이터 수정
     * @param cancelReq
     */
    @Override
    @Transactional
    public void cancelData(CancelReq cancelReq) {
        OrderPayInfo orderPayInfo = paymentBizService.getOrderPayInfo(cancelReq.getOrdNo());
        long rfndAvlAmt = orderPayInfo.getPayAmt() - cancelReq.getCancelAmt();
        //해당 데이터에 취소금액 환불금액 update
        OpPayInfo setModifyData = OpPayInfo.builder().payNo(orderPayInfo.getPayNo()).cnclAmt(cancelReq.getCancelAmt()).rfndAvlAmt(rfndAvlAmt).build();
        paymentBizService.modifyPayRefundAmt(setModifyData);
    }
    /**
     * 이니시스 취소
     * @param cancelReq
     */
    @Override
    public void cancelPay(CancelReq cancelReq) {
        OrderPayInfo orderPayInfo = paymentBizService.getOrderPayInfo(cancelReq.getOrdNo());
        if (OPT0011Enum.REQUEST.getCode().equals(orderPayInfo.getPayPrgsScd())){    //입금전
            //전체환불 API 호출
            InicisRefundRes res = inicisRefundCall(InicisRefundReq.setInicisRefundReq(cancelReq, orderPayInfo));

            long rfndAvlAmt = orderPayInfo.getRfndAvlAmt() - cancelReq.getCancelAmt();
            if (rfndAvlAmt > 0) {
                //남은금액에 대해 결제 요청 API 호출
                PayApproveReq payInfo = setApprovePayInfo(cancelReq, orderPayInfo, rfndAvlAmt);
                approvePay(payInfo);
            }

        } else if (OPT0011Enum.COMPLETE.getCode().equals(orderPayInfo.getPayPrgsScd())) {   //입금후
            if (orderPayInfo.getRfndAvlAmt() == cancelReq.getCancelAmt()) {
                //전체환불 API 호출
                InicisRefundRes res = inicisRefundCall(InicisRefundReq.setInicisRefundReq(cancelReq, orderPayInfo));
            } else {
                InicisPartialRefundRes res = inicisPartialRefundCall(InicisPartialRefundReq.setInicisPartialRefundReq(cancelReq, orderPayInfo));
            }
        }
    }

    //TODO 수정예정 - transactional / try - catch
    @Transactional
    public void savePayInfo(PayApproveReq payInfo, InicisApproveRes res) {
        OpPayInfo basePayInfo = OpPayInfo.of(payInfo, res);
        paymentBizService.savePayInfo(basePayInfo);
    }


    @Override
    public void netCancel(CancelReq cancelReq) {
        log.info("[Inicis.netCancel] Inicis 망취소");
    }

    private InicisApproveRes inicisApproveCall(InicisApproveReq req) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InicisApproveRes> res = restTemplate.postForEntity(APPROVE_URL, HttpUtil.httpEntityMultiValueMap(req), InicisApproveRes.class);
        return res.getBody();
    }

    private InicisRefundRes inicisRefundCall(InicisRefundReq req){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InicisRefundRes> res = restTemplate.postForEntity(REFUND_URL, HttpUtil.httpEntityMultiValueMap(req), InicisRefundRes.class);
        return res.getBody();
    }

    private InicisPartialRefundRes inicisPartialRefundCall(InicisPartialRefundReq req){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InicisPartialRefundRes> res = restTemplate.postForEntity(REFUND_URL, HttpUtil.httpEntityMultiValueMap(req), InicisPartialRefundRes.class);
        return res.getBody();
    }

    private PayApproveReq setApprovePayInfo(CancelReq cancelReq, OrderPayInfo orderPayInfo, long rfndAvlAmt) {
        FranchiseeReq franchiseeReq = FranchiseeReq.builder()
                .clientIp(cancelReq.getClientIp())
                .mid(cancelReq.getMid())
                .url(cancelReq.getUrl())
                .build();
        OrderReq orderReq = OrderReq.builder()
                .ordNo(orderPayInfo.getOrdNo())
                .goodsNm(orderPayInfo.getGoodsNm())
                .prc(rfndAvlAmt)
                .bankCode(BankCode.of(orderPayInfo.getRfndBnkCk()))
                .build();
        MemberReq memberReq = MemberReq.builder()
                .mbrNm(orderPayInfo.getOrdNm())
                .mbrEmail("oBack@plateer.com")      //메일정보 보관안함 -> DB추가필요
                .mbrPhoneNo(orderPayInfo.getOrdSellNo())
                .build();
        return PayApproveReq.inicisApproveOf(franchiseeReq, orderReq, memberReq);
    }
}
