package com.plateer.ec1.common.code.promotion;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PRM0003Enum {
    //DC_CCD
    DISCOUNT("10", "할인금액"),
    DISCOUNT_RATE("20", "할인율")
    ;

    private final String code;
    private final String codeNm;

}
