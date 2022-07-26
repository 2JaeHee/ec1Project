package com.plateer.ec1.common.code.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OPT0012Enum {
    //PROC_CCD 처리구분코드
    SUCCESS("S", "성공"),
    DATA_CREATOR("FD", "데이터생성"),
    VALIDATOR("FV", "유효성검사"),
    PAY("FP", "결제"),
    ;

    @Getter
    private final String code;
    @Getter
    private final String codeNm;
}
