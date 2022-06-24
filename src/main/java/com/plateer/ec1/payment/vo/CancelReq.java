package com.plateer.ec1.payment.vo;

import com.plateer.ec1.payment.enums.PaymentType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CancelReq {
    private PaymentType paymentType;
    private String ordNo;
}
