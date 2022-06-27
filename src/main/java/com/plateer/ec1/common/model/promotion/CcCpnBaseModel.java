package com.plateer.ec1.common.model.promotion;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
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
}
