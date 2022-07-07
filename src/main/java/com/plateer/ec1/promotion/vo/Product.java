package com.plateer.ec1.promotion.vo;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @NotNull
    private String goodsNo;
    @NotNull
    private String itemNo;
    @NotNull
    private Long prc;
    private Long prmNo;
    private Long cpnIssNo;
    private int ordCnt;

    private double calculateDcAmt;

    public void setMaxDcAmtDiscount(double dcVal){
        this.calculateDcAmt = dcVal;
    }
}
