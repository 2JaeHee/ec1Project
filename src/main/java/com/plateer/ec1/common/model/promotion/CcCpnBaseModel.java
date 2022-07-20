package com.plateer.ec1.common.model.promotion;

import com.plateer.ec1.common.code.promotion.PromotionConstants;
import com.plateer.ec1.common.validator.DateValidator;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.function.Predicate;

@Builder
@Getter
public class CcCpnBaseModel {
    private Long prmNo;
    private String cpnKindCd;   //PRM0004 쿠폰종류코드 (상품, 중복, 장바구니)
    private String degrCcd;     //PRM0012 차수구분코드 (1,2,3,4차)
    private String dcSvCcd;     //PRM0005 할인적립구분코드 (할인,적립)
    private String mdaGb;       //PRM0006 매체구분코드(PC, WEB, APP)
    private String entChnGb;    //PRM0007 진입채널구분(NONE, MAIL, SMS, PUSH, HOMEPAGE)
    private String dwlAvlStrtDd;
    private String dwlAvlEndDd;
    private Integer dwlStdDd;
    private Integer dwlPsbCnt;
    private Integer psnDwlPsbCnt;
    private String dwlAvlPlc;   //PRM0008 다운로드가능공간(상품상세, 쿠폰북, 장바구니)
    private String issWayCcd;   //PRM0009 발급방식구분코드(다운로드, 자동발급)
    private String certCd;
    private Double ourChrgRt;
    private Double entrChrgRt;



    public boolean periodValidate() {
        return DateValidator.isPeriodValid
                .test(LocalDate.parse(this.dwlAvlStrtDd, PromotionConstants.YYYYMMDD), LocalDate.parse(this.dwlAvlEndDd, PromotionConstants.YYYYMMDD));
    }

    public boolean isDwlPsbCntValid(int cnt) {
        return this.getDwlPsbCnt() > cnt;
    }

    public boolean isPsnDwlPsbCntValid(int cnt) {
        return this.getPsnDwlPsbCnt() > cnt;
    }
}
