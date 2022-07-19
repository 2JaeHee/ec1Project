package com.plateer.ec1.common.code.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OPT0002Enum {
    //OPT_SYS_CCD 시스템구분코드
    BO("10", "BO"),
    FO("20", "FO")
    ;

    @Getter
    private final String code;
    @Getter
    private final String codeNm;
}
