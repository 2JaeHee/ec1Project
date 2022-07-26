package com.plateer.ec1.common.code.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public enum OPT0001Enum {
    //OPT_TP_CD 주문유형코드
    GENERAL("10", "일반주문"),
    ECOUPON("20", "모바일쿠폰주문")
    ;

    @Getter
    private final String code;
    @Getter
    private final String codeNm;

    private static final Map<String, String> CODE_MAP = Collections.unmodifiableMap(Stream.of(values())
            .collect(Collectors.toMap(OPT0001Enum::getCode, OPT0001Enum::name)));

    public static OPT0001Enum of(String code) {
        return OPT0001Enum.valueOf(CODE_MAP.get(code));
    }
}
