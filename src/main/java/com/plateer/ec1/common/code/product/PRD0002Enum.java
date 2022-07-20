package com.plateer.ec1.common.code.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PRD0002Enum {
    // GOODS_DLV_TP_CD 상품배송유형코드
    DIRECT_DELIVERY("10", "직배송"),
    NO_DELIVERY("20", "무배송")
    ;

    @Getter
    private final String code;
    @Getter
    private final String codeNm;
}
