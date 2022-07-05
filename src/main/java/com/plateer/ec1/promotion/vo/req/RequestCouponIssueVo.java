package com.plateer.ec1.promotion.vo.req;

import com.plateer.ec1.common.code.promotion.PromotionConstants;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class RequestCouponIssueVo {
    @NotNull
    private Long cpnIssNo;
    @NotNull
    private Long prmNo;
    @NotNull
    private String mbrNo;

    @Builder.Default
    private String sysRegrId = PromotionConstants.ADMIN;
    @Builder.Default
    private String sysModrId= PromotionConstants.ADMIN;

}
