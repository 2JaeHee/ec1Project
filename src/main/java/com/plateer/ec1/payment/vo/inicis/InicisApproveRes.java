package com.plateer.ec1.payment.vo.inicis;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class InicisApproveRes implements Serializable {
    private String resultCode;
    private String resultMsg;
    private String tid;
    private String authDate;    //발급일자 [YYYYMMDD]
    private String authTime;    //발급시간 [hhmmss]
    private String vacct;       //가상계좌번호
    private String vacctName;   //예금주명
    private String vacctBankCode;   //가상계좌 발급은행코드
    private String validDate;   //가상계좌 입금예정일자
    private String validTime;   //가상계좌 입금예정시간
    private Long price;       //결제금액

    private String inputName;
    private String refundAcct;
    private String refundBankCode;
    private String refundAcctName;
}
