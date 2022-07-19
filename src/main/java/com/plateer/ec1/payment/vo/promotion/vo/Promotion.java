package com.plateer.ec1.payment.vo.promotion.vo;

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

    private double calculateDcAmt;      //할인금액
    private String maxBenefitYn;
    private String applyPrmYn;

    //TODO 개선고민
    public void calculateAmtDiscount(Product product){
        double paramDcVal = getParamDcVal(product);
        this.calculateDcAmt = getCalculateDcAmt(paramDcVal);
    }

    public double getCalculateDcAmt(double paramDcVal) {
        return this.maxDcAmt > paramDcVal ? paramDcVal : this.maxDcAmt;
    }

    private double getParamDcVal(Product product) {
        double paramDcVal = 0;
        if (PRM0003Enum.DISCOUNT.getCode().equals(this.dcCcd)) {
            paramDcVal = this.dcVal;
        } else if (PRM0003Enum.DISCOUNT_RATE.getCode().equals(this.dcCcd)){
            paramDcVal = product.getPrc() * (this.dcVal / 100);
        }
        return paramDcVal;
    }

    public void setMaxBenefitYn(Long prmNo){
        this.maxBenefitYn = PromotionConstants.N;
        if (Objects.equals(prmNo, this.prmNo)) {
            this.maxBenefitYn = PromotionConstants.Y;
        }
    }

    public void setApplyPrmYn() {
        this.applyPrmYn = PromotionConstants.Y;
    }
    public void setCalculateDcAmt(double amt) {
        this.calculateDcAmt = amt;
    }
}
