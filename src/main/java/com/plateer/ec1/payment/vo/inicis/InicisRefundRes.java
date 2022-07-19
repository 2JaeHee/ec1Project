package com.plateer.ec1.payment.vo.inicis;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InicisRefundRes {
    private String resultCode;
    private String resultMsg;
    private String cancelDate;
    private String cancelTime;
    private String cshrCancelNum;
    private String detailResultCode;
    private String receiptInfo;
}
