package com.plateer.ec1.payment.controller;

import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.service.PaymentService;
import com.plateer.ec1.payment.vo.PayCompleteReq;
import com.plateer.ec1.payment.vo.inicis.InicisCompleteReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping(value = "/approveComplete", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void approveComplete(InicisCompleteReq req){
        //IP 체크
        String SUCCESS_CODE = "0200";
        if(SUCCESS_CODE.equals(req.getType_msg())){
            paymentService.completePay(PayCompleteReq.builder()
                    .paymentType(PaymentType.INICIS).trsnId(req.getNo_req_tid()).build());
        }
    }
}
