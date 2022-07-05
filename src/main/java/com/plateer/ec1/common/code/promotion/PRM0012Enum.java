package com.plateer.ec1.common.code.promotion;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PRM0012Enum {
    //DEGR_CCD 차수구분코드
    ONE("1", "1차"),
    TWO("2", "2차"),
    THREE("3" ,"3차"),
    FOUR("4" ,"4차")
    ;

    private final String code;
    private final String codeNm;
}
