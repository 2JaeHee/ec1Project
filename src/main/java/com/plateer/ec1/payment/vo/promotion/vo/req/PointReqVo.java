package com.plateer.ec1.payment.vo.promotion.vo.req;

import com.plateer.ec1.common.code.promotion.PRM0011Enum;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PointReqVo {
    private PRM0011Enum svUseCcd;
    private String mbrNo;
    private double pntBlc;
    private String ordNo;
    private String payNo;
}
