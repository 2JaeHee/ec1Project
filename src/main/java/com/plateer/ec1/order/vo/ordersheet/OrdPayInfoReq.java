package com.plateer.ec1.order.vo.ordersheet;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrdPayInfoReq {
    //결제정보
    private Long payAmt;
    private String payCcd;
}
