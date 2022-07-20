package com.plateer.ec1.common.code.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PRD0001Enum {
    // GOODS_SELL_TP_CD 상품판매유형코드
    GENERAL("10", "일반상품"),
    MOBILE("20", "모바일쿠폰상품")
    ;

    @Getter
    private final String code;
    @Getter
    private final String codeNm;
}
