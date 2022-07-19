package com.plateer.ec1.payment.vo.inicis;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InicisPartialRefundRes {
    private String resultCode;
    private String resultMsg;
    private String tid;
    private String prtcTid;
    private String prtcPrice;
    private String prtcRemains;
    private String prtcCnt;
    private String prtcType;
    private String prtcDate;
    private String prtcTime;
    private String pointAmount;
    private String discountAmount;
    private String creditAmount;
}
