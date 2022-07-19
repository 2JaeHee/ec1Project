package com.plateer.ec1.common.code.payment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PaymentConfigValue {

    public static String inicisApikey;

    @Value("${inicis.apikey}")
    public void setInicisApiKey(String value) {
        inicisApikey = value;
    }
}
