package com.plateer.ec1.order.strategy;

import com.plateer.ec1.common.code.order.OPT0001Enum;
import com.plateer.ec1.order.vo.OrderDto;
import com.plateer.ec1.order.vo.OrderProductView;
import com.plateer.ec1.order.vo.OrderReq;

public interface DataStrategy {

    OrderDto create(OrderReq orderRequest, OrderProductView orderProductView);

    OPT0001Enum getType();
}
