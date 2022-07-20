package com.plateer.ec1.payment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plateer.ec1.common.code.order.OPT0009Enum;
import com.plateer.ec1.common.code.order.OPT0010Enum;
import com.plateer.ec1.common.code.order.OPT0011Enum;
import com.plateer.ec1.common.utils.HttpUtil;
import com.plateer.ec1.payment.enums.BankCode;
import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.vo.inicis.InicisApproveReq;
import com.plateer.ec1.payment.vo.inicis.InicisApproveRes;
import com.plateer.ec1.payment.vo.PayApproveReq;
import com.plateer.ec1.payment.vo.franchisee.FranchiseeReq;
import com.plateer.ec1.payment.vo.member.MemberReq;
import com.plateer.ec1.payment.vo.order.OrderReq;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

public class InicisCallTest {
    private static final String url = "https://iniapi.inicis.com/api/v1/formpay";
    @Test
    void callTest() {
        PayApproveReq payInfo = setDefaultData();

        InicisApproveReq req = InicisApproveReq.of(payInfo);

        RestTemplate restTemplate = new RestTemplate();
        InicisApproveRes inicisApproveRes = restTemplate.postForEntity(url, HttpUtil.httpEntityMultiValueMap(req), InicisApproveRes.class).getBody();
        assert inicisApproveRes != null;
        assertThat(inicisApproveRes.getResultCode()).isEqualTo("00");
    }

    private PayApproveReq setDefaultData() {
        //상품 가맹점 정보
        FranchiseeReq franchiseeReq = FranchiseeReq.builder()
                .clientIp("127.0.0.1")
                .mid("INIpayTest")
                .url("https://test.com")
                .build();
        //주문 정보
        OrderReq orderReq = OrderReq.builder()
                .ordNo("O202207120002")
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

        return PayApproveReq.inicisApproveOf(franchiseeReq, orderReq, memberReq);
    }
}
