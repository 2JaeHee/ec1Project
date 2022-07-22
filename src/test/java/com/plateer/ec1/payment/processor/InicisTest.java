package com.plateer.ec1.payment.processor;

import com.plateer.ec1.common.model.product.PrGoodsBaseModel;
import com.plateer.ec1.payment.enums.BankCode;
import com.plateer.ec1.payment.enums.InicisResultCode;
import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.factory.Payment;
import com.plateer.ec1.payment.factory.PaymentFactory;
import com.plateer.ec1.payment.mapper.PaymentMapper;
import com.plateer.ec1.payment.vo.*;
import com.plateer.ec1.payment.vo.franchisee.FranchiseeReq;
import com.plateer.ec1.payment.vo.member.MemberReq;
import com.plateer.ec1.payment.vo.order.OrderReq;
import com.plateer.ec1.product.mapper.ProductMapper;
import com.plateer.ec1.product.vo.PrGoodsBaseVo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InicisTest {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    PaymentMapper paymentMapper;
    @Autowired
    PaymentFactory paymentFactory;


    @Test
    @DisplayName("이니시스 가상계좌채번")
    void approve() {
        //1. 주문 데이터 생성해놔야함 op_ord_base
        //2. 주문 상품 데이터 생성해야함
        //주문정보 / 결제정보 리스트
        PayApproveReq payInfo = setDefaultData();

        Payment inicisPayment = this.paymentFactory.getPaymentFactory(PaymentType.INICIS);
        ApproveRes approveRes = inicisPayment.approvePay(payInfo);

        Assertions.assertThat(approveRes.getResultCd()).isEqualTo(InicisResultCode.SUCCESS.getCode());
    }

    @Test
    @DisplayName("이니시스 취소 데이터 (결제 취소 전 클레임 호출)")
    void cancelData() {
        //주문번호
        String reqOrdNo = "O202207220001";
        long reqCancelAmt = 1000;

        CancelReq cancelReq = CancelReq.builder().ordNo(reqOrdNo).cancelAmt(reqCancelAmt).build();

        Payment inicisPayment = this.paymentFactory.getPaymentFactory(PaymentType.INICIS);
        inicisPayment.cancelData(cancelReq);    //환불가능금액 update

        OrderPayInfoRes orderPayInfo = paymentMapper.getOrderPayInfo(OrderPayInfoReq.inicis(reqOrdNo));
        Assertions.assertThat(orderPayInfo.getCnclAmt()).isEqualTo(reqCancelAmt);
    }

    @Test
    @DisplayName("입금 전 전액 취소")
    void beforeDepositCancelAll() {

    }

    @Test
    @DisplayName("입금 전 부분 취소")
    void beforeDepositCancelPartial() {
        String reqOrdNo = "O202207220001";
        long reqCancelAmt = 1000;
        String climNo = "C202207220001";

        String clientIp = "127.0.0.1";
        String mid = "INIpayTest";
        String url = "https://test.com";

        CancelReq cancelReq = CancelReq.builder()
                .ordNo(reqOrdNo).clmNo(climNo).cancelAmt(reqCancelAmt)
                .clientIp(clientIp).mid(mid).url(url)
                .build();

        Payment inicisPayment = this.paymentFactory.getPaymentFactory(PaymentType.INICIS);
        inicisPayment.cancelPay(cancelReq);

    }
    private PayApproveReq setDefaultData() {
        //상품정보
        PrGoodsBaseVo goodsBaseInfo = productMapper.getPrGoodsBaseInfo("P001");
        //상품 가맹점 정보
        FranchiseeReq franchiseeReq = FranchiseeReq.builder()
                .clientIp("127.0.0.1")
                .mid("INIpayTest")
                .url("https://test.com")
                .build();
        //주문 정보
        OrderReq orderReq = OrderReq.builder()
                .ordNo("O202207220001")
                .goodsNm(goodsBaseInfo.getGoodsNm())
                .prc(goodsBaseInfo.getPrmPrc())
                .bankCode(BankCode.IBK)
                .build();

        //회원정보에서 가져오기
        MemberReq memberReq = MemberReq.builder()
                .mbrNm("오백이")
                .mbrEmail("oBack@plateer.com")
                .mbrPhoneNo("01011112222")
                .build();

        return PayApproveReq.inicisApproveOf(franchiseeReq, orderReq, memberReq);
    }
}
