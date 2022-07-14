package com.plateer.ec1.common.code.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OPT0011Enum {
    //PAY_PRGS_SCD  결제진행상태코드
    REQUEST("10", "결제요청"),
    COMPLETE("20", "결제완료"),
    REFUND("30", "환불완료")
   ;

    @Getter
    private final String code;
    @Getter
    private final String codeNm;
}
