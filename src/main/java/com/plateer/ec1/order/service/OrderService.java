package com.plateer.ec1.order.service;


import com.plateer.ec1.order.vo.OrderDto;
import com.plateer.ec1.order.vo.OrderReq;

public interface OrderService {
    OrderDto order(OrderReq orderRequest);
}
