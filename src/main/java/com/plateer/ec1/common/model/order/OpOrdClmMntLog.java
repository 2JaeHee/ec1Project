package com.plateer.ec1.common.model.order;

import com.plateer.ec1.common.code.order.OPT0012Enum;
import com.plateer.ec1.common.code.promotion.PromotionConstants;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class OpOrdClmMntLog {
    private Integer logSeq;
    private String ordNo;
    private String clmNo;
    private String reqPram;
    private String insData;
    private String uptData;
    private String procCcd;
    private LocalDateTime sysRegDtime;
    @Builder.Default
    private String sysRegrId = PromotionConstants.ADMIN;
    private LocalDateTime sysModDtime;
    @Builder.Default
    private String sysModrId = PromotionConstants.ADMIN;


    //데이터생성
    public static OpOrdClmMntLog createOf(String ordNo, String reqPram) {
        return OpOrdClmMntLog.builder()
                .ordNo(ordNo)
                .reqPram(reqPram)
                .procCcd(OPT0012Enum.DATA_CREATOR.getCode())
                .build();
    }
}
