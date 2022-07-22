package com.plateer.ec1.payment.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum InicisResultCode {
    SUCCESS("00", "성공"),
    ;

    @Getter
    private final String code;
    @Getter
    private final String codeNm;
}
