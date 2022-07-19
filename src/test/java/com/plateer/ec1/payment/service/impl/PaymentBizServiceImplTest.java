package com.plateer.ec1.payment.service.impl;

import com.plateer.ec1.common.code.order.OPT0011Enum;
import com.plateer.ec1.common.model.order.OpPayInfo;
import com.plateer.ec1.payment.mapper.PaymentMapper;
import com.plateer.ec1.payment.mapper.PaymentTrxMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PaymentBizServiceImplTest {

    @Autowired
    PaymentMapper paymentMapper;
    @Autowired
    PaymentTrxMapper paymentTrxMapper;

    @Test
    @DisplayName("transId로 결제정보 조회")
    void getPayInfo() {
        String trsnId = "INIAPIVBNKINIpayTest20220714132544122179";
        OpPayInfo payInfo = paymentMapper.getPayInfo(trsnId);
        Assertions.assertThat(payInfo.getPayNo()).isEqualTo("1000003");
    }

    @Test
    @DisplayName("transId로 결제정보 조회 후 결제데이터 수정 - 입금통보 데이터")
    void completePay() {
        String trsnId = "INIAPIVBNKINIpayTest20220714132712822142";
        OpPayInfo getPayInfo = paymentMapper.getPayInfo(trsnId);

        OpPayInfo opPayInfo = OpPayInfo.builder()
                .payNo(getPayInfo.getPayNo())
                .rfndAvlAmt(getPayInfo.getPayAmt())
                .payPrgsScd(OPT0011Enum.COMPLETE.getCode())
                .build();
        paymentTrxMapper.modifyPayInfo(opPayInfo);

        OpPayInfo result = paymentMapper.getPayInfo(trsnId);
        Assertions.assertThat(result.getPayCmtDtime()).isNotNull();

    }
}