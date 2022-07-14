package com.plateer.ec1.order.enums.inicis;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum InicisPaymethod {
    //Type  결제구분코드
    CARD("Card", "신용카드"),
    ACCT("Acct", "실시간계좌이체"),
    VACCT("Vacct", "가상계좌"),
    GVACCT("GVacct", "가상계좌 (입금전, 채번취소 시 사용)"),
    HPP("HPP", "휴대폰"),
    RECEIPT("Receipt", "현금영수증"),
    VOUCHER("Voucher", "상품권")
    ;

    @Getter
    private final String code;
    @Getter
    private final String codeNm;



}
