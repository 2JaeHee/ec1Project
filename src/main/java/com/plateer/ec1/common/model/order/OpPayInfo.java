package com.plateer.ec1.common.model.order;

import com.plateer.ec1.common.code.promotion.PromotionConstants;
import com.plateer.ec1.payment.vo.InicisApproveRes;
import com.plateer.ec1.payment.vo.PayInfo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class OpPayInfo {
    private String payNo;
    private String ordNo;
    private String clmNo;
    private String payMnCd;
    private String payCcd;
    private String payPrgsScd;
    private Long payAmt;
    private long cnclAmt;
    private long rfndAvlAmt;
    private String trsnId;
    private LocalDateTime payCmtDtime;
    private LocalDateTime sysRegDtime;
    @Builder.Default
    private String sysRegrId = PromotionConstants.ADMIN;
    private LocalDateTime sysModDtime;
    @Builder.Default
    private String sysModrId= PromotionConstants.ADMIN;
    private String orgPayNo;
    private String vrAcct;
    private String vrAcctNm;
    private String vrBnkCd;
    private String vrValDt;
    private String vrValTt;

    public static OpPayInfo of(PayInfo payInfo) {
        return OpPayInfo.builder()
                .ordNo(payInfo.getOrdNo())
                .payAmt(payInfo.getPrc())
                .payMnCd(payInfo.getPayMnCd())
                .payCcd(payInfo.getPayCcd())
                .payPrgsScd(payInfo.getPayPrgsScd())
                .build();
    }
    /*
    public void setPayStatus(PayInfo payInfo) {
        this.payCcd = payInfo.getPayCcd();
        this.payPrgsScd = payInfo.getPayPrgsScd();
    }
    */
    public void setInicisRes(InicisApproveRes res) {
        this.payAmt = res.getPrice();
        this.vrAcct = res.getVacct();
        this.vrAcctNm = res.getVacctName();
        this.vrBnkCd = res.getVacctBankCode();
        this.vrValDt = res.getValidDate();
        this.vrValTt = res.getValidTime();
    }
}
