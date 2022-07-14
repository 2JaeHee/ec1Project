package com.plateer.ec1.payment.vo;

import com.plateer.ec1.common.validator.ValidEnum;
import com.plateer.ec1.payment.enums.PaymentType;
import lombok.Builder;
import lombok.Getter;
@Builder
@Getter
public class PayCompleteReq {
    @ValidEnum(enumClass = PaymentType.class)
    private PaymentType paymentType;

    private String payNo;
    private String payPrgsScd;
    private String trsnId;
}
