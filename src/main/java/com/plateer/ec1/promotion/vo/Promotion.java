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

    private Long cpnIssNo;

    private double calculateDcAmt;

    private String maxBenefitYn;
    private String applyPrmYn;

    public void calculateAmtDiscount(Product product){
        if (PRM0003Enum.DISCOUNT.getCode().equals(this.getDcCcd())) {
            setMaxDcAmtDiscount();
        } else if (PRM0003Enum.DISCOUNT_RATE.getCode().equals(this.getDcCcd())){
            setMaxDcAmtDiscountRate(product.getPrc());
        }
    }
    private void setMaxDcAmtDiscount() {
        if(this.maxDcAmt > this.dcVal){
            this.calculateDcAmt = this.dcVal;
        }else{
            this.calculateDcAmt = this.maxDcAmt;
        }
    }

    public void setMaxDcAmtDiscountRate(Long prc){
        double calculateAmt = prc * (this.dcVal / 100);
        if(this.maxDcAmt > calculateAmt){
            this.calculateDcAmt = calculateAmt;
        }else{
            this.calculateDcAmt = this.maxDcAmt;
        }
    }

    public void setMaxBenefitYn(Long prmNo){
        this.maxBenefitYn = PromotionConstants.N;
        if (Objects.equals(prmNo, this.prmNo)) {
            this.maxBenefitYn = PromotionConstants.Y;
        }
    }

    public void setApplyPrmYn(Long prmNo, Long cpnIssNo) {
        this.applyPrmYn = PromotionConstants.N;
        if (Objects.equals(this.prmNo, prmNo) && Objects.equals(this.cpnIssNo, cpnIssNo)) {
            this.applyPrmYn = PromotionConstants.Y;
        }
    }
}
