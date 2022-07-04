package com.plateer.ec1.promotion.vo.res;

import com.plateer.ec1.promotion.vo.CartCouponVo;
import lombok.Builder;
import lombok.Getter;

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
