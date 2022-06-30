package com.plateer.ec1.common.model.member;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class EtMbrBaseModel {
    private String mbrNo;
    private String mbrId;
    private String mbrNm;
    private LocalDateTime sysRegDtime;
    private LocalDateTime sysModDtime;
    private String sysRegrId;
    private String sysModrId;

}
