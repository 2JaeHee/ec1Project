package com.plateer.ec1.order.strategy.impl;

import com.plateer.ec1.common.code.order.OPT0001Enum;
import com.plateer.ec1.order.strategy.DataStrategy;
import com.plateer.ec1.order.vo.OrderDto;
import com.plateer.ec1.order.vo.OrderProductView;
import com.plateer.ec1.order.vo.OrderReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GeneralDataStrategy implements DataStrategy {
    @Override
    public OrderDto create(OrderReq orderRequest, OrderProductView orderProductView) {
        log.info("GeneralDataStrategy.create - 일반주문 데이터 생성");
        return new OrderDto();
    }

    @Override
    public OPT0001Enum getType() {
        return OPT0001Enum.GENERAL;
    }
}
