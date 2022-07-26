package com.plateer.ec1.order.controller;

import com.plateer.ec1.order.service.OrderService;
import com.plateer.ec1.order.vo.OrderDto;
import com.plateer.ec1.order.vo.OrderReq;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping(value = "/orderCreate")
    public OrderDto order(@RequestBody OrderReq orderReq) {
        //주문파라미터 데이터
        orderReq.setBaseInfo();
        //결제인증정보
        //결제 test 정보
//        PayInfo payInfo = new PayInfo();
//        payInfo.setPaymentType(PaymentType.INICIS);
//        orderRequest.setPayInfo(payInfo);

        return orderService.order(orderReq);
    }
}
