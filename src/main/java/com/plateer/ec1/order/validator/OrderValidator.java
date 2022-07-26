package com.plateer.ec1.order.validator;

import com.plateer.ec1.order.enums.OrderType;
import com.plateer.ec1.order.enums.SystemType;
import com.plateer.ec1.order.vo.OrderReq;
import com.plateer.ec1.order.vo.OrderValidationDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.function.Predicate;

@Slf4j
@RequiredArgsConstructor
public enum OrderValidator {
    FO_GENERAL(SystemType.FO, OrderType.GENERAL, OrderCommonValidators.isProductStatusCheck.and(OrderCommonValidators.isAmountVerification)),
    BO_GENERAL(SystemType.BO, OrderType.GENERAL, OrderCommonValidators.isProductStatusCheck),
    FO_ECOUPON(SystemType.FO, OrderType.ECOUPON, OrderCommonValidators.isProductStatusCheck),
    BO_ECOUPON(SystemType.BO, OrderType.ECOUPON, OrderCommonValidators.isProductStatusCheck),
    ;

    @Getter
    private final SystemType systemType;
    @Getter
    private final OrderType orderType;
    @Getter
    private final Predicate<OrderValidationDto> validationDtoPredicate;

    public boolean test(OrderValidationDto orderValidationDto) {
        return validationDtoPredicate.test(orderValidationDto);
    }

    //유형 별 enum get
    public static OrderValidator get(OrderReq request) {
        log.info("[OrderValidator.get] - validator enum 시스템 / 주문유형 별 validation get");
        return Arrays.stream(OrderValidator.values()).filter((e) -> e.systemType.equals(request.getSystemType()) && e.orderType.equals(request.getOrderType()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(""));
    }
}
