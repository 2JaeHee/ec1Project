package com.plateer.ec1.payment.factory.impl;

import com.plateer.ec1.common.code.order.OPT0011Enum;
import com.plateer.ec1.common.model.order.OpPayInfo;
import com.plateer.ec1.common.utils.HttpUtil;
import com.plateer.ec1.payment.enums.BankCode;
import com.plateer.ec1.payment.enums.InicisResultCode;
import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.factory.Payment;
import com.plateer.ec1.payment.mapper.PaymentMapper;
import com.plateer.ec1.payment.mapper.PaymentTrxMapper;
import com.plateer.ec1.payment.vo.*;
import com.plateer.ec1.payment.vo.franchisee.FranchiseeReq;
import com.plateer.ec1.payment.vo.inicis.*;
import com.plateer.ec1.payment.vo.member.MemberReq;
import com.plateer.ec1.payment.vo.order.OrderReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class Inicis implements Payment {
    private final PaymentMapper paymentMapper;
    private final PaymentTrxMapper paymentTrxMapper;

    @Value("${inicis.approve.url}")
    private String APPROVE_URL;

    @Value("${inicis.refund.url}")
    private String REFUND_URL;

    @Override
    public PaymentType getType() {
        return PaymentType.INICIS;
    }

    /**
     * 이니시스 결제 - 가상계좌채번
     * @param payInfo
     * @return
     */
    @Override
    public ApproveRes approvePay(PayApproveReq payInfo) {
        InicisApproveRes inicisApproveRes = inicisApproveCall(InicisApproveReq.of(payInfo));    //가상계좌 call

        if (InicisResultCode.SUCCESS.getCode().equals(inicisApproveRes.getResultCode())) {
            OpPayInfo basePayInfo = OpPayInfo.of(payInfo, inicisApproveRes);
            paymentTrxMapper.savePayInfo(basePayInfo);
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
        OpPayInfo getPayInfo = paymentMapper.getPayInfo(req.getTrsnId());
        paymentTrxMapper.modifyPayInfo(OpPayInfo.completeOf(getPayInfo));
    }

    /**
     * 결제취소데이터 수정
     * @param cancelReq
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public void cancelData(CancelReq cancelReq) {

        OrderPayInfoRes orderPayInfo = paymentMapper.getOrderPayInfo(OrderPayInfoReq.inicis(cancelReq.getOrdNo()));
        paymentTrxMapper.modifyPayRefundAmt(OpPayInfo.cancel(orderPayInfo, cancelReq.getCancelAmt()));
    }

    /**
     * 이니시스 취소
     * @param cancelReq
     */
    @Override
    public void cancelPay(CancelReq cancelReq) {
        //취소 시  결제금액/취소요청금액
        OrderPayInfoRes orderPayInfo = paymentMapper.getOrderPayInfo(OrderPayInfoReq.inicis(cancelReq.getOrdNo()));

        if (OPT0011Enum.REQUEST.getCode().equals(orderPayInfo.getPayPrgsScd())){    //입금전
            String resultCd = "00";
            long rfndAvlAmt = orderPayInfo.getRfndAvlAmt() - cancelReq.getCancelAmt();

            //TODO 수정해야함
            paymentTrxMapper.modifyPayPrgsScd(OpPayInfo.builder()
                    .payNo(orderPayInfo.getPayNo())
                    .payPrgsScd(OPT0011Enum.REFUND.getCode())
                    .build());


            if (rfndAvlAmt > 0) {
                PayApproveReq payInfo = setApprovePayInfo(cancelReq, orderPayInfo, rfndAvlAmt); //남은금액에 대해 결제 요청 API
                ApproveRes approveRes = approvePay(payInfo);
                
                //TODO 수정해야함 결과 실패 시 rollback되게 수정할거임
                OpPayInfo opPayInfo = OpPayInfo.builder().build();
                BeanUtils.copyProperties(orderPayInfo, opPayInfo, "cnclAmt", "rfndAvlAmt", "vrValDt", "vrValTt");
                opPayInfo.cancelComplete(cancelReq);
                paymentTrxMapper.savePayInfo(opPayInfo);
            }
        } else if (OPT0011Enum.COMPLETE.getCode().equals(orderPayInfo.getPayPrgsScd())) {   //입금후
            if (orderPayInfo.getRfndAvlAmt() == cancelReq.getCancelAmt()) {
                inicisRefundCall(InicisRefundReq.of(cancelReq, orderPayInfo));  //전체환불 API
            } else {
                inicisPartialRefundCall(InicisPartialRefundReq.setInicisPartialRefundReq(cancelReq, orderPayInfo)); //부분환불
            }
        }
    }

    //TODO 공통화를 해야함
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

    private PayApproveReq setApprovePayInfo(CancelReq cancelReq, OrderPayInfoRes orderPayInfo, long rfndAvlAmt) {
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
