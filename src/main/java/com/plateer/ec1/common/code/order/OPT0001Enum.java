package com.plateer.ec1.common.code.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OPT0001Enum {
    //OPT_TP_CD 주문유형코드
    GENERAL("10", "일반주문"),
    MOBILE("20", "모바일쿠폰주문")
    ;

    @Getter
    private final String code;
    @Getter
    private final String codeNm;
}
