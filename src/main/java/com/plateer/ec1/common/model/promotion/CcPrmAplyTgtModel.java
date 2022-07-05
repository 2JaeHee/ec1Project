package com.plateer.ec1.common.model.promotion;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CcPrmAplyTgtModel {
    private Long aplyTgtSeq;
    private Long prmNo;
    private String aplyTgtCcd;  //PRM0010 적용대상구분코드 (상품, 기획전, 전시카테고리, 업체)
    private String aplyTgtNo;
    private String useYn;
    private LocalDateTime sysRegDtime;
    private String sysRegrId;
    private LocalDateTime sysModDtime;
    private String sysModrId;
}
