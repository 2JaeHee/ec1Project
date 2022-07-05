package com.plateer.ec1.common.model.promotion;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CcPrmBaseModel {
    private Integer prmNo;
    private String prmNm;
    private String prmKindCd;               //PRM0001 프로모션 종류코드 (가격조정, 쿠폰)
    private String prmPriodCcd;             //PRM0002 프로모션 기간코드( 기간, 기준일)
    private LocalDateTime prmStrtDt;
    private LocalDateTime prmEndDt;
    private int prmStdDd;
    private String empYn;
    private String dcCcd;                   //PRM0003 할인코드 (할인금액, 할인율)
    private double dcVal;
    private int minPurAmt;
    private int maxDcAmt;
    private String useYn;
    private String rmk;
    private LocalDateTime sysRegDtime;
    private String sysRegrId;
    private LocalDateTime sysModDtime;
    private String sysModrId;

    /**
     * 기간체크 (취소일시  < 프로모션종료일시 )
     * @return boolean
     */
    public boolean periodValidate() {
        return LocalDateTime.now().isBefore(this.prmEndDt);
    }

    /**
     * 기준일체크 (쿠폰발급 수정일 + 프로모션 기준일 < 프로모션종료일시 )
     * @return boolean
     */
    public boolean referenceDateValidate(LocalDateTime paramDate) {
        return paramDate.plusDays(this.prmStdDd).isBefore(this.prmEndDt);
    }
}
