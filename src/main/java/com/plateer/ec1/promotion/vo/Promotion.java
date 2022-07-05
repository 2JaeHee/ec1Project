package com.plateer.ec1.promotion.vo;

import com.plateer.ec1.common.code.promotion.PRM0003Enum;
import com.plateer.ec1.common.code.promotion.PromotionConstants;
import com.plateer.ec1.common.validator.Validator;
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

    //TODO 개선고민
    public void calculateAmtDiscount(Product product){
        double paramDcVal = 0;
        if (PRM0003Enum.DISCOUNT.getCode().equals(this.getDcCcd())) {
            paramDcVal = this.dcVal;
        } else if (PRM0003Enum.DISCOUNT_RATE.getCode().equals(this.getDcCcd())){
            paramDcVal = product.getPrc() * (this.dcVal / 100);
        }
        this.calculateDcAmt = Validator.isCompareValid.apply(this.maxDcAmt, paramDcVal);
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
