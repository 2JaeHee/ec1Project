package com.plateer.ec1.promotion.vo.req;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Builder
@Getter
public class ProductReq {
    @NotNull
    private String goodsNo;
    @NotNull
    private String itemNo;
    @NotNull
    private Long prc;
    private Long prmNo;
    private Long cpnIssNo;
}
