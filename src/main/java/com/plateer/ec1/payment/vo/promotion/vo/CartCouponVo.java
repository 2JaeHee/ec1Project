package com.plateer.ec1.payment.vo.promotion.vo;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CartCouponVo {
    private Promotion promotion;
    private List<Product> productList;
    private String maxBenefitYn;

    public static CartCouponVo of(Promotion promotion, List<Product> productList){
        return CartCouponVo.builder()
                .promotion(promotion)
                .productList(productList)
                .build();
    }

    public void setMaxBenefitYn(String maxBenefitYn){
        this.maxBenefitYn = maxBenefitYn;
    }
}
