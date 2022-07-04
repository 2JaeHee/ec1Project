package com.plateer.ec1.promotion.vo;

import com.plateer.ec1.common.code.promotion.PRM0003Enum;
import com.plateer.ec1.common.code.promotion.PromotionConstants;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Promotion {
    private String aplyTgtNo;

    private Long prmNo;
    private String prmNm;
    private String prmKindCd;
    private String prmPriodCcd;
    private LocalDateTime prmStrtDt;
    private LocalDateTime prmEndDt;
    private int prmStdDd;
    private String empYn;
    private String dcCcd;
    private double dcVal;
    private int minPurAmt;
    private int maxDcAmt;
    private String useYn;
    private String rmk;

    private double calculateDcAmt;

    private String maxBenefitYn;
    private String applyPrmYn;

    public void calculateAmtDiscount(Product product){
        if (PRM0003Enum.DISCOUNT.getCode().equals(this.getDcCcd())) {
            this.calculateDcAmt = this.dcVal;
        } else if (PRM0003Enum.DISCOUNT_RATE.getCode().equals(this.getDcCcd())){
            setMaxDcAmtDiscountRate(product.getPrc());
        }
    }

    public void setMaxDcAmtDiscountRate(Long prc){
        this.calculateDcAmt = prc  * (this.dcVal / 100);
    }

    public void setMaxBenefitYn(Long prmNo){
        this.maxBenefitYn = PromotionConstants.N;
        if (Objects.equals(prmNo, this.prmNo)) {
            this.maxBenefitYn = PromotionConstants.Y;
        }
    }

    public void setApplyPrmYn(Long prmNo) {
        this.applyPrmYn = PromotionConstants.N;
        if (Objects.equals(prmNo, this.prmNo)) {
            this.applyPrmYn = PromotionConstants.Y;
        }
    }
}
