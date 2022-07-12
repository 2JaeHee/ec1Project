package com.plateer.ec1.payment.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public enum BankCode {
    IBK("03", "기업은행"),
    KB("04", "국민은행"),
    SH("07", "수협은행"),
    NH("11", "농협은행"),
    WR("20", "우리은행"),
    SC("23", "SC은행"),
    ;

    @Getter
    String code;
    @Getter
    String codeNm;

    BankCode(String code, String codeNm) {
        this.code = code;
        this.codeNm = codeNm;
    }
}