package com.plateer.ec1.promotion.vo;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Product {
    private String goodsNo;
    private String goodsNm;
    private String goodsDlvTpCd;
    private String goodsTpCd;
    private Long prc;
    private Integer prmNo;

    private double calculateDcAmt;

    public void setMaxDcAmtDiscount(double dcVal){
        this.calculateDcAmt = this.prc - dcVal;
    }
}
