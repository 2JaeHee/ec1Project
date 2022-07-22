package com.plateer.ec1.common.utils;

import com.plateer.ec1.common.code.PaymentConfigValue;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class AES256UtilTest {
    @Value("${inicis.apikey}")
    private String apikey;

    @Value("${inicis.iv}")
    private String iv;
    @Test
    void encrypt() throws Exception {
        String str = "01011112222";
        String apikey = PaymentConfigValue.inicisApikey;
        String iv = PaymentConfigValue.inicisIv;

        String encryptedStr = AesAnDesUtil.encryptAes(str, apikey, iv);
        String decryptedStr = AesAnDesUtil.decryptAes(encryptedStr, apikey, iv);
        log.info("encryptedStr : {}" , encryptedStr);
        log.info("decryptedStr : {}" , decryptedStr);
        Assertions.assertThat(str).isEqualTo(decryptedStr);
    }
}