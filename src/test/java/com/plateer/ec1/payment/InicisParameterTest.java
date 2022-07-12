package com.plateer.ec1.payment;

import com.plateer.ec1.payment.enums.BankCode;
import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.service.PaymentService;
import com.plateer.ec1.payment.vo.PayInfo;
import com.plateer.ec1.payment.vo.franchisee.FranchiseeReq;
import com.plateer.ec1.payment.vo.member.MemberReq;
import com.plateer.ec1.payment.vo.order.OrderReq;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InicisParameterTest {

    @Autowired
    private PaymentService paymentService;

    @Test
    @DisplayName("INICIS approve")
    void approve_inicis(){
        PayInfo payInfo = PayInfo.builder()
                .paymentType(PaymentType.INICIS)
                .build();
        setDefaultData(payInfo);
        paymentService.approve(payInfo);
    }

    private void setDefaultData(PayInfo payInfo) {
        //상품 가맹점 정보
        FranchiseeReq franchiseeReq = FranchiseeReq.builder()
                .clientIp("111222333")
                .mid("franchiseeId")
                .url("https://test.com")
                .build();
        //주문 정보
        OrderReq orderReq = OrderReq.builder()
                .ordNo("O202207120001")
                .goodsNm("양말")
                .prc(1000L)
                .bankCode(BankCode.IBK)
                .build();

        //회원정보에서 가져오기
        MemberReq memberReq = MemberReq.builder()
                .mbrNm("오백이")
                .mbrEmail("oBack@plateer.com")
                .mbrPhoneNo("01011112222")
                .build();

        payInfo.setFranchiseeInfo(franchiseeReq);
        payInfo.setOrderInfo(orderReq);
        payInfo.setMemberInfo(memberReq);
    }
}
