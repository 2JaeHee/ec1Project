package com.plateer.ec1.common.model.product;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PrGoodsBaseModel {
    private String goodsNo;
    private String goodsNm;
    private String goodsDlvTpCd;
    private String goodsTpCd;
    private Long salePrc;
    private Long prmPrc;
    private String prgsStatCd;
    private LocalDateTime sysRegDtime;
    private LocalDateTime sysModDtime;
    private String sysRegrId;
    private String sysModrId;
}
