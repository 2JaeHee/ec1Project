package com.plateer.ec1.common.model.order;

import com.plateer.ec1.common.code.promotion.PromotionConstants;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class OpOrdBase {
    private String ordNo;
    private String mbrNo;
    private String ordTpCd;
    private String ordSysCcd;
    private LocalDateTime ordReqDtime;
    private LocalDateTime ordCmtDtime;
    private String ordNm;
    private String ordSellNo;
    private String ordAddr;
    private String ordAddrDtl;
    private String rfndBnkCk;
    private String rfndAcctNo;
    private String rfndAcctOwnNm;
    @Builder.Default
    private String sysRegrId = PromotionConstants.ADMIN;
    private LocalDateTime sysModDtime;
    @Builder.Default
    private String sysModrId= PromotionConstants.ADMIN;

}
