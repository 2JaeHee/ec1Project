package com.plateer.ec1.common.code.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public enum OPT0002Enum {
    //OPT_SYS_CCD 시스템구분코드
    BO("10", "BO"),
    FO("20", "FO");

    @Getter
    private final String code;
    @Getter
    private final String codeNm;

    private static final Map<String, String> CODE_MAP = Collections.unmodifiableMap(Stream.of(values())
            .collect(Collectors.toMap(OPT0002Enum::getCode, OPT0002Enum::name)));

    public static OPT0002Enum of(String code) {
        return OPT0002Enum.valueOf(CODE_MAP.get(code));
    }
}
