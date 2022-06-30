package com.plateer.ec1.promotion.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Product {
    private String goodsNo;
    private String goodsNm;
    private String goodsDlvTpCd;
    private String goodsTpCd;
    private Long prc;

    private Long calculateDcAmt;
}
