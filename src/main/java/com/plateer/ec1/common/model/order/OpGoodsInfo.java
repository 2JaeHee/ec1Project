package com.plateer.ec1.common.model.order;

import com.plateer.ec1.common.code.promotion.PromotionConstants;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class OpGoodsInfo {
    private String ordNo;
    private String ordGoodsNo;
    private String ordItemNo;
    private String goodsSellTpCd;
    private String goodsDlvTpCd;
    private String goodsNm;
    private String itemNm;
    private Long sellAmt;
    private Long sellDcAmt;
    private LocalDateTime sysRegDtime;
    @Builder.Default
    private String sysRegrId = PromotionConstants.ADMIN;
    private LocalDateTime sysModDtime;
    @Builder.Default
    private String sysModrId = PromotionConstants.ADMIN;

}
