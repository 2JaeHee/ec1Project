package com.plateer.ec1.common.code.payment;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class PaymentConfigValueTest {

    @Test
    @DisplayName("설정파일 값 get")
    void getInicisApiKey() {
        log.info("=== apikey : {}", PaymentConfigValue.inicisApikey);
        Assertions.assertThat(PaymentConfigValue.inicisApikey).isNotNull();
    }
}