package com.plateer.ec1.payment.vo.inicis;

import com.plateer.ec1.common.code.PaymentConfigValue;
import com.plateer.ec1.common.utils.AesAnDesUtil;
import com.plateer.ec1.order.enums.inicis.InicisPayType;
import com.plateer.ec1.order.enums.inicis.InicisPaymethod;
import com.plateer.ec1.payment.vo.CancelReq;
import com.plateer.ec1.payment.vo.OrderPayInfoRes;
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

    public static InicisRefundReq of(CancelReq cancelReq, OrderPayInfoRes orderPayInfo) {
        InicisRefundReq refundReq = InicisRefundReq.builder()
                .type(InicisPayType.REFUND.getCode())
                .paymethod(InicisPaymethod.VACCT.getCode())
                .timestamp(LocalDateTime.now().format(df))
                .clientIp(cancelReq.getClientIp())
                .mid(cancelReq.getMid())
                .tid(orderPayInfo.getTrsnId())
                .msg(cancelReq.getCancelMsg())
                .refundAcctNum(setRfndAcctNo(orderPayInfo.getRfndAcctNo()))
                .refundBankCode(orderPayInfo.getRfndBnkCk())
                .refundAcctName(orderPayInfo.getRfndAcctOwnNm())
                .build();

        refundReq.setHashData();
        return refundReq;
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

    private static String setRfndAcctNo(String rfndAcctNo) {
        return AesAnDesUtil.encryptAes(rfndAcctNo, PaymentConfigValue.inicisApikey, PaymentConfigValue.inicisIv);
    }
}
