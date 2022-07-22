package com.plateer.ec1.promotion.vo;

import com.plateer.ec1.common.code.promotion.PRM0003Enum;
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


    public void calculateAmtDiscount(Promotion promotion){
        double paramDcVal = getParamDcVal(promotion);
        this.calculateDcAmt = paramDcVal * ordCnt;
    }

    private double getParamDcVal(Promotion promotion) {
        double paramDcVal = 0;
        if (PRM0003Enum.DISCOUNT.getCode().equals(promotion.getDcCcd())) {
            paramDcVal = promotion.getDcVal();
        } else if (PRM0003Enum.DISCOUNT_RATE.getCode().equals(promotion.getDcCcd())){
            paramDcVal = this.getPrc() * (promotion.getDcVal() / 100);
        }
        return paramDcVal;
    }

}
