package com.plateer.ec1.order.strategy;

import com.plateer.ec1.common.code.order.OPT0002Enum;
import com.plateer.ec1.order.vo.OrderDto;
import com.plateer.ec1.order.vo.OrderReq;

public interface AfterStrategy {

    void call(OrderReq orderRequest, OrderDto orderDto);

    OPT0002Enum getType();
}
