package com.plateer.ec1.order.strategy.impl;

import com.plateer.ec1.common.code.order.OPT0002Enum;
import com.plateer.ec1.order.strategy.AfterStrategy;
import com.plateer.ec1.order.vo.OrderDto;
import com.plateer.ec1.order.vo.OrderReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BoAfterStrategy implements AfterStrategy {
    @Override
    public void call(OrderReq orderRequest, OrderDto orderDto) {
        log.info("[BoAfterStrategy.call] - BO주문 후처리");
    }

    @Override
    public OPT0002Enum getType() {
        return OPT0002Enum.BO;
    }

}
