package com.plateer.ec1.payment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plateer.ec1.common.utils.HttpUtil;
import com.plateer.ec1.payment.enums.BankCode;
import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.vo.InicisApproveReq;
import com.plateer.ec1.payment.vo.InicisApproveRes;
import com.plateer.ec1.payment.vo.PayInfo;
import com.plateer.ec1.payment.vo.franchisee.FranchiseeReq;
import com.plateer.ec1.payment.vo.member.MemberReq;
import com.plateer.ec1.payment.vo.order.OrderReq;
import lombok.Setter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.DataInput;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class InicisCallTest {
    private static final String url = "https://iniapi.inicis.com/api/v1/formpay";
    @Test
    void callTest() throws JsonProcessingException {
        PayInfo payInfo = PayInfo.builder()
                .paymentType(PaymentType.INICIS)
                .payMnCd("10")
                .payCcd("10")
                .payPrgsScd("10")
                .build();

        setDefaultData(payInfo);
        InicisApproveReq req = InicisApproveReq.of(payInfo);

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForEntity(url, HttpUtil.httpEntityMultiValueMap(req), String.class).getBody();

        ObjectMapper mapper = new ObjectMapper();
        InicisApproveRes inicisApproveRes = mapper.readValue(response, InicisApproveRes.class);
        assertThat(inicisApproveRes.getResultCode()).isEqualTo("00");
    }

    private void setDefaultData(PayInfo payInfo) {
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
