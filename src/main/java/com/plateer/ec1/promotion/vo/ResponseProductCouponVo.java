package com.plateer.ec1.promotion.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Builder
@Getter
@Setter
public class ResponseProductCouponVo extends ResponseBaseVo {
    private List<ProductCouponVo> productCouponVoList;

    public static ResponseProductCouponVo of(List<ProductCouponVo> productCouponVoList){
        return ResponseProductCouponVo.builder()
                .productCouponVoList(productCouponVoList)
                .build();
    }

}
