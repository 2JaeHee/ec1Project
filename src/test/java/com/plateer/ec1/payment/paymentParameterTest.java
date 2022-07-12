package com.plateer.ec1.payment;

import com.plateer.ec1.payment.enums.BankCode;
import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.vo.PayInfo;
import com.plateer.ec1.payment.vo.franchisee.FranchiseeReq;
import com.plateer.ec1.payment.vo.member.MemberReq;
import com.plateer.ec1.payment.vo.order.OrderReq;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class paymentParameterTest {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("가맹점정보체크_기본값체크 o")
    void franchiseeInfo_true() {
        FranchiseeReq franchiseeReq = FranchiseeReq.builder()
                .clientIp("111222333")
                .mid("franchiseeId")
                .url("https://test.com")
                .build();
        Set<ConstraintViolation<FranchiseeReq>> violations = validator.validate(franchiseeReq);
        assertTrue(violations.isEmpty());
    }
    @Test
    @DisplayName("가맹점정보체크_기본값체크 x")
    void franchiseeInfo_false() {
        FranchiseeReq franchiseeReq = FranchiseeReq.builder()
                .clientIp("111222333")
                .mid("franchiseeId")
//                .url("https://test.com")
                .build();
        Set<ConstraintViolation<FranchiseeReq>> violations = validator.validate(franchiseeReq);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("주문정보체크_기본값체크 o")
    void orderInfo_true() {
        OrderReq orderReq = OrderReq.builder()
                .ordNo("O202207120001")
                .goodsNm("양말")
                .prc(1000L)
                .bankCode(BankCode.IBK)
                .build();
        Set<ConstraintViolation<OrderReq>> violations = validator.validate(orderReq);
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("주문정보체크_기본값체크 x")
    void orderInfo_false() {
        OrderReq orderReq = OrderReq.builder()
                .ordNo("O202207120001")
                .goodsNm("양말")
                .prc(1000L)
//                .bankCode(BankCode.IBK)
                .build();
        Set<ConstraintViolation<OrderReq>> violations = validator.validate(orderReq);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("회원정보체크")
    void memberInfo_true() {
        MemberReq memberReq = MemberReq.builder()
                .mbrNm("오백이")
                .mbrEmail("oBack@plateer.com")
                .mbrPhoneNo("01011112222")
                .build();

        Set<ConstraintViolation<MemberReq>> violations = validator.validate(memberReq);
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("회원정보체크")
    void memberInfo_false() {
        MemberReq memberReq = MemberReq.builder()
                .mbrNm("오백이")
//                .mbrEmail("oBack@plateer.com")
                .mbrPhoneNo("01011112222")
                .build();

        Set<ConstraintViolation<MemberReq>> violations = validator.validate(memberReq);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("파라미터체크")
    void parameter(){
        //상품 가맹점 정보
        FranchiseeReq franchiseeReq = FranchiseeReq.builder()
                .clientIp("111222333")
                .mid("franchiseeId")
                .url("https://test.com")
                .build();
        //주문 정보
        OrderReq orderReq = OrderReq.builder()
                .ordNo("O202207120001")
                .goodsNm("양말")
                .prc(1000L)
                .bankCode(BankCode.IBK)
                .build();

        //회원정보에서 가져오기
        MemberReq memberReq = MemberReq.builder()
                .mbrNm("오백이")
                .mbrEmail("oBack@plateer.com")
                .mbrPhoneNo("01011112222")
                .build();

        PayInfo payInfo = PayInfo.builder()
                .paymentType(PaymentType.INICIS)
                .build();

        payInfo.setFranchiseeInfo(franchiseeReq);
        payInfo.setOrderInfo(orderReq);
        payInfo.setMemberInfo(memberReq);

        Set<ConstraintViolation<PayInfo>> violations = validator.validate(payInfo);
        assertTrue(violations.isEmpty());
    }
}
