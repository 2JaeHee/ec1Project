package com.plateer.ec1.common.model.order;

import com.plateer.ec1.common.code.promotion.PromotionConstants;
import com.plateer.ec1.payment.vo.inicis.InicisApproveRes;
import com.plateer.ec1.payment.vo.PayApproveReq;
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

    public static OpPayInfo of(PayApproveReq payInfo) {
        return OpPayInfo.builder()
                .ordNo(payInfo.getOrdNo())
                .payAmt(payInfo.getPrc())
                .payMnCd(payInfo.getPayMnCd().getCode())
                .payCcd(payInfo.getPayCcd().getCode())
                .payPrgsScd(payInfo.getPayPrgsScd().getCode())
                .build();
    }
    public void setInicisRes(InicisApproveRes res) {
        this.trsnId = res.getTid();
        this.payAmt = res.getPrice();
        this.vrAcct = res.getVacct();
        this.vrAcctNm = res.getVacctName();
        this.vrBnkCd = res.getVacctBankCode();
        this.vrValDt = res.getValidDate();
        this.vrValTt = res.getValidTime();
    }
}
