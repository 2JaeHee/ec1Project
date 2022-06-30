package com.plateer.ec1.promotion.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Promotion {
    //private Long couponIssueNo;
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

    private String goodsNo;
    private String goodsNm;
    private String goodsDlvTpCd;
    private String goodsTpCd;
    private Long prc;

    private double calculateDcAmt;

    private String maxBenefitYn;

    public void setMaxDcAmt(Long prc){
        this.calculateDcAmt = prc - this.dcVal;
    }

    public void setMaxBenefitYn(String maxBenefitYn){
        this.maxBenefitYn = maxBenefitYn;
    }


}
