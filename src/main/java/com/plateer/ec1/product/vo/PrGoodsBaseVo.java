package com.plateer.ec1.product.vo;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PrGoodsBaseVo {
    private String goodsNo;
    private String goodsNm;
    private String goodsDlvTpCd;
    private String goodsTpCd;
    private Long prc;
    private Long salePrc;
    private Long prmPrc;
    private String prgsStatCd;
    private LocalDateTime sysRegDtime;
    private LocalDateTime sysModDtime;
    private String sysRegrId;
    private String sysModrId;
}
