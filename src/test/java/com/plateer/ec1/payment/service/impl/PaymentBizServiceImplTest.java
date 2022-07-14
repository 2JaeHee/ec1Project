package com.plateer.ec1.payment.service.impl;

import com.plateer.ec1.common.model.order.OpPayInfo;
import com.plateer.ec1.payment.mapper.PaymentMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PaymentBizServiceImplTest {

    @Autowired
    PaymentMapper paymentMapper;

    @Value("${inicis.apikey}")
    private String apikey;

    @Test
    void getPayInfo() {
        String trsnId = "INIAPIVBNKINIpayTest20220714132544122179";
        OpPayInfo payInfo = paymentMapper.getPayInfo(trsnId);
        Assertions.assertThat(payInfo.getPayNo()).isEqualTo("1000003");
    }

    @Test
    void test() {
        System.out.println(apikey);
    }
}