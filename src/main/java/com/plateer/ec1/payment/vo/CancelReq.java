package com.plateer.ec1.payment.vo;

import com.plateer.ec1.payment.enums.PaymentType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Builder
public class CancelReq {
    private PaymentType paymentType;
    //상품가맹점정보
    private String clientIp;    //가맹점 요청 서버IP
    private String mid;         //상점아이디
    private String url;         //가맹점 URL
    //취소정보
    private String ordNo;
    private String clmNo;
    private long cancelAmt;
    private String cancelMsg;

}
