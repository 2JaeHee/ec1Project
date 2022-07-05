package com.plateer.ec1.common.code.promotion;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PRM0004Enum {
    //CPN_KIND_CD / 쿠폰종류코드
    PRODUCT_COUPON("10", "상품쿠폰"),
    DUPLICATE_COUPON("20", "중복쿠폰"),
    CART_COUPON("30", "장바구니쿠폰")
    ;

    private final String code;
    private final String codeNm;
}
