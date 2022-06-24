package com.plateer.ec1.order.validator;


import com.plateer.ec1.order.vo.OrderValidationDto;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Predicate;

@Slf4j
public class OrderTypeValidators {

    public static Predicate<OrderValidationDto> isSellingProduct = (orderRequest) -> {
        log.info("[OrderProductValidators] 주문 유형 validation");
        return orderRequest != null;
    };
}
