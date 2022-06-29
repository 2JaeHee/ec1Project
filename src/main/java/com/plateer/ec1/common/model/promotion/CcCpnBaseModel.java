package com.plateer.ec1.common.model.promotion;

import com.plateer.ec1.common.code.promotion.PromotionConstants;
import com.plateer.ec1.common.validator.Validator;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

@Getter
public class CcCpnBaseModel {
    private Long prmNo;
    private String cpnKindCd;
    private String degrCcd;
    private String dcSvCcd;
    private String mdaGb;
    private String entChnGb;
    private String dwlPriodCcd;
    private String dwlAvlStrtDd;
    private String dwlAvlEndDd;
    private int dwlStdDd;
    private int dwlPsbCnt;
    private int psnDwlPsbCnt;
    private String dwlAvlPlc;
    private String issWayCcd;
    private String certCd;
    private double ourChrgRt;
    private double entrChrgRt;
    private Timestamp sysRegDtime;
    private String sysRegrId;
    private Timestamp sysModDtime;
    private String sysModrId;



    public boolean periodValidate() {
        return Validator.isPeriodValid
                .test(LocalDate.parse(this.dwlAvlStrtDd, PromotionConstants.YYYYMMDD), LocalDate.parse(this.dwlAvlEndDd, PromotionConstants.YYYYMMDD));
    }

    public Predicate<Integer> dwlPsbCntValid = (cnt) -> this.getDwlPsbCnt() > cnt;

    public Predicate<Integer> psnDwlPsbCntValid = (cnt) -> this.getPsnDwlPsbCnt() > cnt;

}
