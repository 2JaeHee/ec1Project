package com.plateer.ec1.common.code.promotion;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PRM0001Enum {
    //PRM_KIND_CD 프로모션종류코드
    PRICE("10", "가격조정"),
    COUPON("20", "쿠폰")
    ;

    private final String code;
    private final String codeNm;
}
