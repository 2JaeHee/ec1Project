package com.plateer.ec1.order.vo.ordersheet;

import com.plateer.ec1.common.model.order.OpOrdBnfInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class OrdGoodsInfoReq {
    private String ordGoodsNo;
    private String ordItemNo;
    private String goodsSellTpCd;
    private int ordCnt;
    private List<OrdBnfInfoReq> ordBnfInfoReqList;   //상품별혜태정보
}
