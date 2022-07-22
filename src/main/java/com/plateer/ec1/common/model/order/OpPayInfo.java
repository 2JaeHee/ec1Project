package com.plateer.ec1.common.model.order;

import com.plateer.ec1.common.code.order.OPT0010Enum;
import com.plateer.ec1.common.code.order.OPT0011Enum;
import com.plateer.ec1.common.code.promotion.PromotionConstants;
import com.plateer.ec1.payment.vo.CancelReq;
import com.plateer.ec1.payment.vo.OrderPayInfoRes;
import com.plateer.ec1.payment.vo.inicis.InicisApproveRes;
import com.plateer.ec1.payment.vo.PayApproveReq;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
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

    //이니시스 승인
    public static OpPayInfo of(PayApproveReq payInfo, InicisApproveRes res) {
        return OpPayInfo.builder()
                .ordNo(payInfo.getOrdNo())
                .payAmt(payInfo.getPrc())
                .payMnCd(payInfo.getPayMnCd().getCode())
                .payCcd(payInfo.getPayCcd().getCode())
                .payPrgsScd(payInfo.getPayPrgsScd().getCode())
                .trsnId(res.getTid())
                .payAmt(res.getPrice())
                .vrAcct(res.getVacct())
                .vrAcctNm(res.getVacctName())
                .vrBnkCd(res.getVacctBankCode())
                .vrValDt(res.getValidDate())
                .vrValTt(res.getValidTime())
                .build();
    }
    //이니시스 결제 완료
    public static OpPayInfo completeOf(OpPayInfo opPayInfo) {
        return OpPayInfo.builder()
                .payNo(opPayInfo.getPayNo())
                .rfndAvlAmt(opPayInfo.getPayAmt())
                .payPrgsScd(OPT0011Enum.COMPLETE.getCode())
                .build();
    }

    //이니시스 취소
    public static OpPayInfo cancel(OrderPayInfoRes orderPayInfo, Long cancelAmt) {
        return OpPayInfo.builder()
                .payNo(orderPayInfo.getPayNo())
                .cnclAmt(cancelAmt)
                .rfndAvlAmt(orderPayInfo.getPayAmt() - cancelAmt)
                .build();
    }

    //이니시스 취소
    public void cancelComplete(CancelReq cancelReq) {
        this.clmNo = cancelReq.getClmNo();
        this.payCcd = OPT0010Enum.CANCEL.getCode();
        this.orgPayNo = this.payNo;
        this.payCcd = OPT0010Enum.CANCEL.getCode();
        this.payPrgsScd = OPT0011Enum.REFUND.getCode();
    }
}
