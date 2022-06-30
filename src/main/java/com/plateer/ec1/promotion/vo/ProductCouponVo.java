package com.plateer.ec1.promotion.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Getter
@Builder
public class ProductCouponVo {
    private Product product;
    private List<AvailablePromotionResVo> promotionList;
    //Promotion Vo -> AvailablePromotionResVo Vo로

    public static ProductCouponVo of(Product product, List<AvailablePromotionResVo> promotionList){
        return ProductCouponVo.builder()
                .product(product)
                .promotionList(promotionList)
                .build();
    }

}
