package com.plateer.ec1.common.code;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PaymentConfigValue {

    public static String inicisApikey;
    public static String inicisIv;


    @Value("${inicis.apikey}")
    public void setInicisApiKey(String value) {
        inicisApikey = value;
    }

    @Value("${inicis.iv}")
    public void setInicisIv(String value) {
        inicisIv = value;
    }
}
