package com.plateer.ec1.order.validator;

import com.plateer.ec1.order.vo.OrderProductView;
import com.plateer.ec1.order.vo.OrderReq;
import com.plateer.ec1.order.vo.OrderValidationDto;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Predicate;

@Slf4j
public class OrderBasicValidators {
    public static Predicate<OrderValidationDto> isCouponApplicableCheck(OrderReq orderRequest, OrderProductView orderProductView) {
        log.info("[OrderBasicValidators] 주문 기본 validation - 쿠폰적용 가능여부 체크");
        return (OrderRequest) -> orderRequest != null;
    }

    public static Predicate<OrderValidationDto> isShippingAddressCheck(OrderReq orderRequest, OrderProductView orderProductView) {
        log.info("[OrderBasicValidators] 주문 기본 validation - 배송지 유무 체크");
        return (OrderRequest) -> orderRequest != null;
    }

    public static Predicate<OrderValidationDto> isSystemOrderTypeCheck(OrderReq orderRequest, OrderProductView orderProductView) {
        log.info("[OrderBasicValidators] 주문 기본 validation - 시스템별 가능한 주문유형 체크");
        return (OrderRequest) -> orderRequest != null;
    }
}
