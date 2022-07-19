package com.plateer.ec1.payment.vo;

import com.plateer.ec1.payment.enums.PaymentType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CancelReq {
    //상품가맹점정보
    @NotEmpty
    private String clientIp;    //가맹점 요청 서버IP
    @NotEmpty
    private String mid;         //상점아이디
    @NotNull
    private String url;         //가맹점 URL

    private PaymentType paymentType;
    private String ordNo;
    private String clmNo;
    private long cancelAmt;
    private String cancelMsg;       //취소요청사유
}
