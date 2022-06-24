package com.plateer.ec1.claim.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Getter
@RequiredArgsConstructor
public enum ClaimType {
    GCC(ClaimProcessorType.COMPLETE, "C", Arrays.asList("10"), new ArrayList<>(), true,  "", new ArrayList<>()),
    MCA(ClaimProcessorType.ACCEPT_WITHDRAWAL, "C",Arrays.asList("20"), new ArrayList<>(), true,  "", new ArrayList<>()),
    MCC(ClaimProcessorType.COMPLETE, "C", new ArrayList<>(), new ArrayList<>(), true, "", new ArrayList<>()),
    RA(ClaimProcessorType.ACCEPT_WITHDRAWAL, "R", Arrays.asList("50"), new ArrayList<>(), true,  "", new ArrayList<>()),
    RC(ClaimProcessorType.COMPLETE, "RC", new ArrayList<>(), new ArrayList<>(), true, "", new ArrayList<>()),
    RW(ClaimProcessorType.ACCEPT_WITHDRAWAL, "RC", Arrays.asList("60"), new ArrayList<>(), true,  "", new ArrayList<>()),
    EA(ClaimProcessorType.ACCEPT_WITHDRAWAL, "RC", new ArrayList<>(), new ArrayList<>(), true,  "", new ArrayList<>()),
    EW(ClaimProcessorType.ACCEPT_WITHDRAWAL, "X", Arrays.asList("50"), new ArrayList<>(), true, "", new ArrayList<>()),
    ;

    private final ClaimProcessorType claimType;
    private final String claimCode;     //주문클레임구분코드
    private final List<String> productTypes; // 상품유형 :모바일/일반
    private final List<String> validStatusList;
    private final Boolean claimNoFlag;
    private final String orderStateCode;
    private final List<String> deliveryCode;

    public static ClaimType findClaimTypeByDto(String climTypeCode, String productTypeCode){
        return Arrays.stream(ClaimType.values())
                .filter(t -> t.getClaimCode().equals(climTypeCode) && t.getProductTypes().contains(productTypeCode))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(""));
    }

}
