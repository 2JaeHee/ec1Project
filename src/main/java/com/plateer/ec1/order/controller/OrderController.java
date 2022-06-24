package com.plateer.ec1.order.controller;

import com.plateer.ec1.order.service.OrderService;
import com.plateer.ec1.order.vo.OrderDto;
import com.plateer.ec1.order.vo.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    public OrderDto order(OrderRequest orderRequest) {
        //주문파라미터 데이터
//        orderRequest.setOrderType(OrderType.GENERAL);
//        orderRequest.setSystemType(SystemType.BO);
        //결제인증정보
        //결제 test 정보
//        PayInfo payInfo = new PayInfo();
//        payInfo.setPaymentType(PaymentType.INICIS);
//        orderRequest.setPayInfo(payInfo);

        return orderService.order(orderRequest);
    }
}
