package com.plateer.ec1.common.code.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OPT0009Enum {
    //PAY_MN_CD 결제수단코드
    VIRTUAL_ACCOUNT("10", "가상계좌"),
    POINT("20", "포인트")
    ;

    @Getter
    private final String code;
    @Getter
    private final String codeNm;
}
