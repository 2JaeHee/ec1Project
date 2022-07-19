package com.plateer.ec1.payment.vo.promotion.vo.req;

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
    private Long prmNo;
    @NotNull
    private String mbrNo;

    private Long cpnIssNo;

    @Builder.Default
    private String sysRegrId = PromotionConstants.ADMIN;
    @Builder.Default
    private String sysModrId= PromotionConstants.ADMIN;

}
