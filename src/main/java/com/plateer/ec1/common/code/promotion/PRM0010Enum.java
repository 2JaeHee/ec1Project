package com.plateer.ec1.common.code.promotion;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PRM0010Enum {
    //APLY_TGT_CCD 프로모션종류코드
    PRODUCT("10", "상품"),
    COUPON("20", "쿠폰")
    ;

    private final String code;
    private final String codeNm;
}
