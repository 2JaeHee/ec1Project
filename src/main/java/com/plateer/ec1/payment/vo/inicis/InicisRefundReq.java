package com.plateer.ec1.payment.vo.inicis;

import com.plateer.ec1.common.code.payment.PaymentConfigValue;
import com.plateer.ec1.common.model.order.OpPayInfo;
import com.plateer.ec1.common.utils.AesAnDesUtil;
import com.plateer.ec1.order.enums.inicis.InicisPayType;
import com.plateer.ec1.order.enums.inicis.InicisPaymethod;
import com.plateer.ec1.payment.vo.CancelReq;
import com.plateer.ec1.payment.vo.OrderPayInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@Getter
@Setter
public class InicisRefundReq {
    public static final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private String type;
    private String paymethod;
    private String timestamp;
    private String clientIp;
    private String mid;
    private String tid;
    private String msg;
    private String refundAcctNum;
    private String refundBankCode;
    private String refundAcctName;
    private String hashData;    //hash(INIAPIKey+type+paymethod+timestamp+clientIp+mid+tid+refundAcctNum)

    public static InicisRefundReq of(CancelReq cancelReq, OrderPayInfo orderPayInfo) {
        return InicisRefundReq.builder()
                .type(InicisPayType.REFUND.getCode())
                .paymethod(InicisPaymethod.VACCT.getCode())
                .timestamp(LocalDateTime.now().format(df))
                .clientIp(cancelReq.getClientIp())
                .mid(cancelReq.getMid())
                .tid(orderPayInfo.getTrsnId())
                .msg(cancelReq.getCancelMsg())
                .refundAcctNum(orderPayInfo.getRfndAcctNo())
                .refundBankCode(orderPayInfo.getRfndBnkCk())
                .refundAcctName(orderPayInfo.getRfndAcctOwnNm())
                .build();
    }

    public void setHashData() {
        String hashData = PaymentConfigValue.inicisApikey +
                this.type +
                this.paymethod +
                this.timestamp +
                this.clientIp +
                this.mid +
                this.tid +
                this.refundAcctNum;
        this.hashData = AesAnDesUtil.encodeSha(hashData);
    }
    public static InicisRefundReq setInicisRefundReq(CancelReq cancelReq, OrderPayInfo orderPayInfo) {
        InicisRefundReq refundReq = InicisRefundReq.of(cancelReq, orderPayInfo);
        refundReq.setHashData();
        return refundReq;
    }
}
