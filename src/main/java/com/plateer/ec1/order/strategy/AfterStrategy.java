package com.plateer.ec1.order.strategy;

import com.plateer.ec1.order.enums.SystemType;
import com.plateer.ec1.order.vo.OrderDto;
import com.plateer.ec1.order.vo.OrderRequest;

public interface AfterStrategy {

    void call(OrderRequest orderRequest, OrderDto orderDto);

    SystemType getType();
}
