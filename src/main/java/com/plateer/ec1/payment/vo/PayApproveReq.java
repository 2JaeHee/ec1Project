package com.plateer.ec1.payment.vo;

import com.plateer.ec1.common.code.order.OPT0009Enum;
import com.plateer.ec1.common.code.order.OPT0010Enum;
import com.plateer.ec1.common.code.order.OPT0011Enum;
import com.plateer.ec1.common.validator.ValidEnum;
import com.plateer.ec1.payment.enums.BankCode;
import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.vo.franchisee.FranchiseeReq;
import com.plateer.ec1.payment.vo.member.MemberReq;
import com.plateer.ec1.payment.vo.order.OrderReq;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
public class PayApproveReq {
    private String payNo;
    @ValidEnum(enumClass = PaymentType.class)
    private PaymentType paymentType;
    //상품가맹점정보
    @NotEmpty
    private String clientIp;    //가맹점 요청 서버IP
    @NotEmpty
    private String mid;         //상점아이디
    @NotEmpty
    private String url;         //가맹점 URL
    //주문정보
    @NotEmpty
    private String ordNo;
    @NotEmpty
    private String goodsNm;
    @NotNull
    private Long prc;
    @ValidEnum(enumClass = BankCode.class)
    private BankCode bankCode;        //은행코드 enum 필요
    //회원정보
    @NotEmpty
    private String mbrNm;
    @NotEmpty
    private String mbrEmail;
    @NotEmpty
    private String mbrPhoneNo;

    //결제정보코드
    private OPT0009Enum payMnCd;     //결제수단
    private OPT0010Enum payCcd;      //결제구분코드
    private OPT0011Enum payPrgsScd;  //결제진행상태코드

    public void setFranchiseeInfo(FranchiseeReq franchiseeReq) {
        this.clientIp = franchiseeReq.getClientIp();
        this.mid = franchiseeReq.getMid();
        this.url = franchiseeReq.getUrl();
    }

    public void setOrderInfo(OrderReq orderReq) {
        this.ordNo = orderReq.getOrdNo();
        this.goodsNm = orderReq.getGoodsNm();
        this.prc = orderReq.getPrc();
        this.bankCode = orderReq.getBankCode();
    }

    public void setMemberInfo(MemberReq memberReq) {
        this.mbrNm = memberReq.getMbrNm();
        this.mbrEmail = memberReq.getMbrEmail();
        this.mbrPhoneNo = memberReq.getMbrPhoneNo();
    }

}
