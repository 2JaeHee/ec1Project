package com.plateer.ec1.order.service;


import com.plateer.ec1.common.code.order.OPT0001Enum;
import com.plateer.ec1.common.code.order.OPT0002Enum;
import com.plateer.ec1.order.strategy.AfterStrategy;
import com.plateer.ec1.order.strategy.DataStrategy;
import com.plateer.ec1.order.vo.OrderDto;
import com.plateer.ec1.order.vo.OrderReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderContext orderContext;
    private final Map<OPT0001Enum, DataStrategy> dataStrategyMap = new HashMap<>();
    private final Map<OPT0002Enum, AfterStrategy> afterStrategyMap = new HashMap<>();

    public OrderServiceImpl(OrderContext orderContext, List<DataStrategy> dataStrategies, List<AfterStrategy> afterStrategies) {
        this.orderContext = orderContext;
        dataStrategies.forEach(c -> dataStrategyMap.put(c.getType(), c));
        afterStrategies.forEach(c -> afterStrategyMap.put(c.getType(), c));
    }

    @Override
    public OrderDto order(OrderReq orderRequest) {
        DataStrategy dataStrategy = getDataStrategy(orderRequest);
        AfterStrategy afterStrategy = getAfterStrategy(orderRequest);
        log.info("----------- DataStrategy :" + dataStrategy);
        log.info("----------- AfterStrategy :" +afterStrategy);
        return orderContext.excute(dataStrategy, afterStrategy, orderRequest);
    }

    private DataStrategy getDataStrategy(OrderReq orderRequest) {
        return dataStrategyMap.get(orderRequest.getOrderType());
    }

    private AfterStrategy getAfterStrategy(OrderReq orderRequest) {
        return afterStrategyMap.get(orderRequest.getSystemType());
    }

}
