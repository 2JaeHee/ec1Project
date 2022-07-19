package com.plateer.ec1.order.enums.inicis;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum InicisPayType {
    //Type  결제구분코드
    PAY("Pay", "결제"),
    REFUND("Refund", "가상계좌환불"),
    PARTIAL_REFUND("PartialRefund", "가상계좌 부분환불")
    ;

    @Getter
    private final String code;
    @Getter
    private final String codeNm;
}
