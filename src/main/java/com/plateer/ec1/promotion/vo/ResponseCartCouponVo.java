package com.plateer.ec1.promotion.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
public class ResponseCartCouponVo extends ResponseBaseVo {

    private List<CartCouponVo> cartCouponVoList;

    public static ResponseCartCouponVo of(List<CartCouponVo> cartCouponVoList){
        return ResponseCartCouponVo.builder()
                .cartCouponVoList(cartCouponVoList)
                .build();
    }
}
