package com.plateer.ec1.payment.vo.promotion.vo;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ProductCouponVo {
    private Product product;
    private List<Promotion> promotionList;

    public static ProductCouponVo of(Product product, List<Promotion> promotionList){
        return ProductCouponVo.builder()
                .product(product)
                .promotionList(promotionList)
                .build();
    }

}
