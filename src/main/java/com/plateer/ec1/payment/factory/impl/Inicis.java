package com.plateer.ec1.payment.factory.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plateer.ec1.common.model.order.OpPayInfo;
import com.plateer.ec1.common.utils.HttpUtil;
import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.factory.Payment;
import com.plateer.ec1.payment.mapper.PaymentTrxMapper;
import com.plateer.ec1.payment.vo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class Inicis implements Payment {
    private final PaymentTrxMapper paymentMapper;
    private static final String url = "https://iniapi.inicis.com/api/v1/formpay";

    @Override
    @Transactional
    public ApproveResVO approvePay(PayInfo payInfo) {
        //가상계좌 채번
        InicisApproveRes inicisApproveRes = inicisApproveCall(InicisApproveReq.of(payInfo));
        //주문결제 insert
        String SUCCESS_CODE = "00";
        if (SUCCESS_CODE.equals(inicisApproveRes.getResultCode())) {
            savePayInfo(payInfo, inicisApproveRes);
        }
        return ApproveResVO.of(inicisApproveRes);
    }

    @Transactional
    public void savePayInfo(PayInfo payInfo, InicisApproveRes res) {
        OpPayInfo basePayInfo = OpPayInfo.of(payInfo);
        basePayInfo.setInicisRes(res);
        //주문결제테이블 save
        paymentMapper.savePayInfo(basePayInfo);
    }

    @Override
    public void cancelPay(CancelReq cancelReq) {
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

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForEntity(url, HttpUtil.httpEntityMultiValueMap(req), String.class).getBody();

        ObjectMapper mapper = new ObjectMapper();
        InicisApproveRes inicisApproveRes = new InicisApproveRes();
        try {
            inicisApproveRes = mapper.readValue(response, InicisApproveRes.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //외부인터페이스 사용 후 로그에 결과값 업데이트
        return inicisApproveRes;
    }

    private InicisCancelRes inicisCancelCall(InicisCancelReq inicisCancelReq){
        //외부인터페이스 사용 시 로그추가
        log.info("[Inicis.cancel] 이니시스 취소 api call");
        InicisCancelRes res = new InicisCancelRes();
        //외부인터페이스 사용 후 로그에 결과값 업데이트
        return res;
    }

}
