package com.plateer.ec1.payment.factory;

import com.plateer.ec1.payment.enums.PaymentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 구현 객체를 생성하는 역할을 하는 Factory
 */
@Slf4j
@Component
public class PaymentFactory {

    private final Map<PaymentType, Payment> paymentMap = new HashMap<>();

    public PaymentFactory(List<Payment> payments) {
        payments.forEach(c -> paymentMap.put(c.getType(), c));
    }

    public Payment getPaymentFactory(PaymentType type) {
        log.info("----------- Payment : " + paymentMap.get(type));
        return paymentMap.get(type);
    }
}
