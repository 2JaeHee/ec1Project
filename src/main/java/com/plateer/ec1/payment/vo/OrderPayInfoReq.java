package com.plateer.ec1.payment.vo;

import com.plateer.ec1.common.code.order.OPT0009Enum;
import com.plateer.ec1.common.code.order.OPT0010Enum;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderPayInfoReq {
    private String ordNo;
    private String payMnCd;
    private String payCcd;

    public static OrderPayInfoReq inicis(String ordNo) {
        return OrderPayInfoReq.builder()
                .ordNo(ordNo)
                .payCcd(OPT0009Enum.VIRTUAL_ACCOUNT.getCode())
                .payCcd(OPT0010Enum.PAY.getCode()).build();
    }

    public static OrderPayInfoReq point(String ordNo) {
        return OrderPayInfoReq.builder()
                .ordNo(ordNo)
                .payCcd(OPT0009Enum.POINT.getCode())
                .payCcd(OPT0010Enum.PAY.getCode()).build();
    }
}
