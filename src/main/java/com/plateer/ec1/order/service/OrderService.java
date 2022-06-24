package com.plateer.ec1.order.service;


import com.plateer.ec1.order.vo.OrderDto;
import com.plateer.ec1.order.vo.OrderRequest;

public interface OrderService {
    OrderDto order(OrderRequest orderRequest);
}
