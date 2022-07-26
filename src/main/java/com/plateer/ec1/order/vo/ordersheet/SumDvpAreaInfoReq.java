package com.plateer.ec1.order.vo.ordersheet;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class SumDvpAreaInfoReq {
    private Integer sumDvpSeq;
    private List<OrdGoodsInfoReq> ordGoodsInfoReqList;
}
