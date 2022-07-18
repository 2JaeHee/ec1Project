package com.plateer.ec1.payment.factory.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plateer.ec1.common.code.order.OPT0011Enum;
import com.plateer.ec1.common.model.order.OpPayInfo;
import com.plateer.ec1.common.utils.HttpUtil;
import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.factory.Payment;
import com.plateer.ec1.payment.mapper.PaymentTrxMapper;
import com.plateer.ec1.payment.service.PaymentBizService;
import com.plateer.ec1.payment.vo.*;
import com.plateer.ec1.payment.vo.inicis.InicisApproveReq;
import com.plateer.ec1.payment.vo.inicis.InicisApproveRes;
import com.plateer.ec1.payment.vo.inicis.InicisCancelReq;
import com.plateer.ec1.payment.vo.inicis.InicisCancelRes;
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
    private static final String url = "https://iniapi.inicis.com/api/v1/formpay";

    /**
     * 이니시스 가상계좌 채번
     * @param payInfo
     * @return
     */
    @Override
    @Transactional
    public ApproveResVO approvePay(PayApproveReq payInfo) {
        //가상계좌 채번
        InicisApproveRes inicisApproveRes = inicisApproveCall(InicisApproveReq.of(payInfo));
        //주문결제 insert
        String SUCCESS_CODE = "00";
        if (SUCCESS_CODE.equals(inicisApproveRes.getResultCode())) {
            savePayInfo(payInfo, inicisApproveRes);
        }
        return ApproveResVO.of(inicisApproveRes);
    }

    /**
     * 이니시스 입금통보
     * @param req
     */
    @Override
    @Transactional
    public void completePay(PayCompleteReq req) {
        OpPayInfo getPayInfo = paymentBizService.getPayInfo(req.getTrsnId());
        OpPayInfo opPayInfo = OpPayInfo.builder()
                .payNo(getPayInfo.getPayNo())
                .rfndAvlAmt(getPayInfo.getPayAmt())
                .payPrgsScd(OPT0011Enum.COMPLETE.getCode())
                .build();
        paymentBizService.modifyPayInfo(opPayInfo);
    }

    /**
     * 결제취소데이터 수정
     * @param cancelReq
     */
    @Override
    @Transactional
    public void cancelData(CancelReq cancelReq) {
        OpPayInfo orderPayInfo = paymentBizService.getOrderPayInfo(cancelReq.getOrdNo());
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
        InicisCancelReq inicisCancelReq = new InicisCancelReq();
        inicisCancelCall(inicisCancelReq);
    }

    @Transactional
    public void savePayInfo(PayApproveReq payInfo, InicisApproveRes res) {
        OpPayInfo basePayInfo = OpPayInfo.of(payInfo);
        basePayInfo.setInicisRes(res);
        paymentBizService.savePayInfo(basePayInfo);
    }


    @Override
    public void netCancel(CancelReq cancelReq) {
        log.info("[Inicis.netCancel] Inicis 망취소");
    }

    @Override
    public PaymentType getType() {
        return PaymentType.INICIS;
    }

    private InicisApproveRes inicisApproveCall(InicisApproveReq req) {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InicisApproveRes> res = restTemplate.postForEntity(url, HttpUtil.httpEntityMultiValueMap(req), InicisApproveRes.class);
        //외부인터페이스 사용 후 로그에 결과값 업데이트
        return res.getBody();
    }

    private InicisCancelRes inicisCancelCall(InicisCancelReq inicisCancelReq){
        //외부인터페이스 사용 시 로그추가
        log.info("[Inicis.cancel] 이니시스 취소 api call");
        InicisCancelRes res = new InicisCancelRes();
        //외부인터페이스 사용 후 로그에 결과값 업데이트
        return res;
    }

}
