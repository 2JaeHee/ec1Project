package com.plateer.ec1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderTest {
//    @Autowired
//    private OrderController orderController;

    @Test
    @DisplayName("BO general order")
    void generalOrderBo(){
//        OrderRequest orderRequest = new OrderRequest();
//        orderRequest.setOrderType(OrderType.GENERAL);
//        orderRequest.setSystemType(SystemType.BO);
//
//        PayApproveReq payInfo = PayApproveReq.builder().build();
//        payInfo.setPaymentType(PaymentType.INICIS);
//        orderRequest.setPayInfo(payInfo);
//
//        orderController.order(orderRequest);
    }

    @Test
    @DisplayName("FO general order")
    void generalOrderFo(){
//        OrderRequest orderRequest = new OrderRequest();
//        orderRequest.setOrderType(OrderType.GENERAL);
//        orderRequest.setSystemType(SystemType.FO);
//
//        PayApproveReq payInfo = PayApproveReq.builder().build();
//        payInfo.setPaymentType(PaymentType.INICIS);
//        orderRequest.setPayInfo(payInfo);
//        orderController.order(orderRequest);
    }

    @Test
    @DisplayName("BO ecoupon order")
    void ecouponOrderBo() {
//        OrderRequest orderRequest = new OrderRequest();
//        orderRequest.setOrderType(OrderType.ECOUPON);
//        orderRequest.setSystemType(SystemType.BO);
//
//        PayApproveReq payInfo = PayApproveReq.builder().build();
//        payInfo.setPaymentType(PaymentType.POINT);
//        orderRequest.setPayInfo(payInfo);
//
//        orderController.order(orderRequest);
    }

    @Test
    @DisplayName("FO ecoupon order")
    void ecouponOrderFo(){
//        OrderRequest orderRequest = new OrderRequest();
//        orderRequest.setOrderType(OrderType.ECOUPON);
//        orderRequest.setSystemType(SystemType.FO);
//
//        PayApproveReq payInfo = PayApproveReq.builder().build();
//        payInfo.setPaymentType(PaymentType.POINT);
//        orderRequest.setPayInfo(payInfo);
//
//        orderController.order(orderRequest);
    }
}
