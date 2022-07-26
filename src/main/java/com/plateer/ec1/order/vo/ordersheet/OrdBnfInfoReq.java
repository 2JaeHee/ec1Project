package com.plateer.ec1.order.vo.ordersheet;

import com.plateer.ec1.common.model.order.OpGoodsInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class OrdBnfInfoReq {
    private Long prmNo;
    private Long cpnIssNo;
    private String cpnKindCd;   //PRM0004 쿠폰종류코드 (상품10, 중복20, 장바구니30)
    private String degrCcd;     //PRM0012 차수구분코드 (1,2,3,4차)
    private List<OrdGoodsInfoReq> ordGoodsInfoReqs; //주문혜택적용상품정보
}
