package com.plateer.ec1.promotion.vo;

import com.plateer.ec1.common.code.promotion.PRM0003Enum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class AvailablePromotionResVo {
    private String aplyTgtNo;

    private Integer prmNo;
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

    public void setMaxDcAmtDiscount(Long prc){
        this.calculateDcAmt = prc - this.dcVal;
    }
    public void setMaxDcAmtDiscountRate(Long prc){
        this.calculateDcAmt = prc/this.dcVal;
    }

    public void setMaxBenefitYn(String maxBenefitYn){
        this.maxBenefitYn = maxBenefitYn;
    }
}
