package com.plateer.ec1.common.code.promotion;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PRM0011Enum {
    //SV_USE_CCD 적립사용구분코드
    ACCUMULATE("10", "적립"),
    USE("20", "사용"),
    CANCEL("30", "취소")
    ;

    @Getter
    private final String code;
    @Getter
    private final String codeNm;
}
