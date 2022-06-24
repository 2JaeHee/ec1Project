package com.plateer.ec1.payment.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Builder
@Getter
@Setter
public class InicisApproveReq {
    private static final String pay = "Pay";
    private static final String vacct = "Vacct";

    private String type;        //가상계좌채번 type : Pay 고정
    private String paymethod;   //가상계좌유형 : Vacct 고정
    private String moid;        //가맹점 주문번호
    private String goodName;    //상품명

    public static InicisApproveReq build(@NonNull PayInfo payInfo){
        return InicisApproveReq.builder()
                .type(pay)
                .paymethod(vacct)
                .moid(payInfo.getOrdNo())
                .goodName(payInfo.getPrdNm())
                .build();
    }

}
