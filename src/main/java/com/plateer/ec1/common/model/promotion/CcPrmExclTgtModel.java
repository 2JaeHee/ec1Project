package com.plateer.ec1.common.model.promotion;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CcPrmExclTgtModel {
    private Long exclTgtSeq;
    private Long prmNo;
    private String exclTgtCcd;
    private String exclTgtNo;
    private String useYn;
    private LocalDateTime sysRegDtime;
    private String sysRegrId;
    private LocalDateTime sysModDtime;
    private String sysModrId;
}
