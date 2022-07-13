package com.plateer.ec1.payment.controller;

import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.service.PaymentService;
import com.plateer.ec1.payment.vo.PayInfo;
import com.plateer.ec1.promotion.vo.req.RequestCouponIssueVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @RequestMapping(value = "/approveComplete", method = RequestMethod.POST)
    public void approveComplete(@RequestBody String param){
        log.info("param : {}", param);
        PayInfo payInfo = PayInfo.builder().build();
        paymentService.completePay(payInfo);
    }
}
