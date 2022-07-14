package com.plateer.ec1.order.enums.inicis;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum InicisPayType {
    //Type  결제구분코드
    PAY("Pay", "결제"),
    REFUND("Refund", "취소")
    ;

    @Getter
    private final String code;
    @Getter
    private final String codeNm;
}
