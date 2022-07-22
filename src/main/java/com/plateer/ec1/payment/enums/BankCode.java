package com.plateer.ec1.payment.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public enum BankCode {
    IBK("03", "기업은행"),
    KB("04", "국민은행"),
    SH("07", "수협은행"),
    NH("11", "농협은행"),
    WR("20", "우리은행"),
    SC("23", "SC은행"),
    ;

    private static final Map<String, String> CODE_MAP = Collections.unmodifiableMap(Stream.of(values())
            .collect(Collectors.toMap(BankCode::getCode, BankCode::name)));

    @Getter
    private final String code;
    @Getter
    private final String codeNm;

    public static BankCode of(String code) {
        return BankCode.valueOf(CODE_MAP.get(code));
    }
}