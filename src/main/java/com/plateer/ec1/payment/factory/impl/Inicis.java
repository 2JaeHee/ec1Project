package com.plateer.ec1.payment.factory.impl;

import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.factory.Payment;
import com.plateer.ec1.payment.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Inicis implements Payment {
    @Override
    public ApproveResVO approve(PayInfo payInfo) {
        log.info("[Inicis.approve] Inicis 승인");
        //가상계좌 채번
        InicisApproveRes inicisApproveRes = inicisApproveCall(InicisApproveReq.build(payInfo));
        return InicisApproveRes.build(inicisApproveRes);
    }

    @Override
    public void cancel(CancelReq cancelReq) {
        //환불요청
        InicisCancelReq inicisCancelReq = new InicisCancelReq();

        inicisCancelCall(inicisCancelReq);
        //입금완료처리

    }

    @Override
    public void netCancel(CancelReq cancelReq) {
        log.info("[Inicis.netCancel] Inicis 망취소");
    }

    @Override
    public PaymentType getType() {
        return PaymentType.INICIS;
    }

    private InicisApproveRes inicisApproveCall(InicisApproveReq req){
        //외부인터페이스 사용 시 로그추가
        log.info("[Inicis.approve] 이니시스 승인 api call");

        InicisApproveRes res = new InicisApproveRes();
        //외부인터페이스 사용 후 로그에 결과값 업데이트
        return res;
    }

    private InicisCancelRes inicisCancelCall(InicisCancelReq inicisCancelReq){
        //외부인터페이스 사용 시 로그추가
        log.info("[Inicis.cancel] 이니시스 취소 api call");
        InicisCancelRes res = new InicisCancelRes();
        //외부인터페이스 사용 후 로그에 결과값 업데이트
        return res;
    }

}
