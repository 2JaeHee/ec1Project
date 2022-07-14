package com.plateer.ec1.common.code.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OPT0010Enum {
    //PAY_CCD  결제구분코드
    PAY("10", "결제"),
    CANCEL("20", "취소")
   ;

    @Getter
    private final String code;
    @Getter
    private final String codeNm;
}
