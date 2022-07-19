package com.plateer.ec1.payment.vo.inicis;

import com.plateer.ec1.common.code.payment.PaymentConfigValue;
import com.plateer.ec1.common.model.order.OpPayInfo;
import com.plateer.ec1.common.utils.AesAnDesUtil;
import com.plateer.ec1.order.enums.inicis.InicisPayType;
import com.plateer.ec1.order.enums.inicis.InicisPaymethod;
import com.plateer.ec1.payment.enums.PaymentType;
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
public class InicisPartialRefundReq {
    public static final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private String type;
    private String paymethod;
    private String timestamp;   //전문생성시간 [YYYYMMDDhhmmss]
    private String clientIp;
    private String mid;
    private String tid;
    private String msg;
    private Long price;
    private Long confirmPrice;
    private String refundAcctNum;
    private String refundBankCode;
    private String refundAcctName;
    private String hashData;       //hash(INIAPIKey+type+paymethod+timestamp+clientIp+mid+tid+price+confirmPrice+refundAcctNum)

    public static InicisPartialRefundReq of(CancelReq cancelReq, OrderPayInfo orderPayInfo) {
        return InicisPartialRefundReq.builder()
                .type(InicisPayType.PARTIAL_REFUND.getCode())
                .paymethod(InicisPaymethod.VACCT.getCode())
                .timestamp(LocalDateTime.now().format(df))
                .clientIp(cancelReq.getClientIp())
                .mid(cancelReq.getMid())
                .tid(orderPayInfo.getTrsnId())
                .msg(cancelReq.getCancelMsg())
                .price(cancelReq.getCancelAmt())
                .confirmPrice(orderPayInfo.getRfndAvlAmt() - cancelReq.getCancelAmt())
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
                this.price +
                this.confirmPrice +
                this.refundAcctNum;
        this.hashData = AesAnDesUtil.encodeSha(hashData);
    }

    public static InicisPartialRefundReq setInicisPartialRefundReq(CancelReq cancelReq, OrderPayInfo orderPayInfo) {
        InicisPartialRefundReq partialRefundReq = InicisPartialRefundReq.of(cancelReq, orderPayInfo);
        partialRefundReq.setHashData();
        return partialRefundReq;
    }
}
