package com.plateer.ec1.payment.vo.inicis;

import com.plateer.ec1.common.code.order.OPT0009Enum;
import com.plateer.ec1.common.code.order.OPT0010Enum;
import com.plateer.ec1.common.code.order.OPT0011Enum;
import com.plateer.ec1.payment.enums.BankCode;
import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.vo.PayApproveReq;
import com.plateer.ec1.payment.vo.franchisee.FranchiseeReq;
import com.plateer.ec1.payment.vo.member.MemberReq;
import com.plateer.ec1.payment.vo.order.OrderReq;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class InicisApproveReqTest {
    @Value("${inicis.apikey}")
    private String apikey;

    @Test
    void test() {
        log.info("apikey : {}", apikey);
    }

    @Test
    void setHashData() {
        PayApproveReq payInfo = PayApproveReq.builder()
                .paymentType(PaymentType.INICIS)
                .payMnCd(OPT0009Enum.VIRTUAL_ACCOUNT)
                .payCcd(OPT0010Enum.PAY)
                .payPrgsScd(OPT0011Enum.REQUEST)
                .build();
        setDefaultData(payInfo);

        InicisApproveReq inicisApproveReq = InicisApproveReq.of(payInfo);
        inicisApproveReq.setHashData();

        log.info("test : {} ", inicisApproveReq.getHashData());
    }

    private void setDefaultData(PayApproveReq payInfo) {
        //상품 가맹점 정보
        FranchiseeReq franchiseeReq = FranchiseeReq.builder()
                .clientIp("127.0.0.1")
                .mid("INIpayTest")
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