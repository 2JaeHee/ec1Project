package com.plateer.ec1.common.model.promotion;

import com.plateer.ec1.common.code.promotion.PromotionConstants;
import lombok.*;

import java.time.LocalDateTime;
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CcCpnIssueModel {
    private Long cpnIssNo;
    private Long prmNo;
    private String mbrNo;
    private LocalDateTime cpnUseDt;
    private Long orgCpnIssNo;
    private String cpnCertNo;
    private String ordNo;
    private LocalDateTime sysRegDtime;
    @Builder.Default
    private String sysRegrId = PromotionConstants.ADMIN;
    private LocalDateTime sysModDtime;
    @Builder.Default
    private String sysModrId= PromotionConstants.ADMIN;
}
