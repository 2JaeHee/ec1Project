package com.plateer.ec1.order.validator;


import com.plateer.ec1.order.vo.OrderValidationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Slf4j
@Component
public class OrderCommonValidators {

    public static Predicate<OrderValidationDto> isProductStatusCheck = (orderRequest) -> {
        log.info("[OrderCommonValidators] 주문 공통 validation - 상품 판매상태 체크");
        return orderRequest != null;
    };

    public static Predicate<OrderValidationDto> isProductStockCheck = (orderRequest) -> {
        log.info("[OrderCommonValidators] 주문 공통 validation - 상품 재고 체크");
        return orderRequest != null;
    };

    public static Predicate<OrderValidationDto> isPurchaseRestriction = (orderRequest) -> {
        log.info("[OrderCommonValidators] 주문 공통 validation - 구매제한 체크");
        return orderRequest != null;
    };

    public static Predicate<OrderValidationDto> isAmountVerification = (orderRequest) -> {
        log.info("[OrderCommonValidators] 주문 공통 validation - 주문 금액 검증");
        log.info("----------------------- sum(주문상품금액) + sum(배송비용) - sum(혜택) = sum(결제)");
        return orderRequest != null;
    };
}
