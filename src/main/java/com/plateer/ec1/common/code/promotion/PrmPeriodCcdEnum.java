package com.plateer.ec1.common.code.promotion;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PrmPeriodCcdEnum {
    PERIOD("10", "기간"),
    REFERENCE_DATE("20", "기준일")
    ;

    private final String code;
    private final String codeNm;

}
