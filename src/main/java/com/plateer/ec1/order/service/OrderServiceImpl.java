package com.plateer.ec1.order.service;


import com.plateer.ec1.order.enums.OrderType;
import com.plateer.ec1.order.enums.SystemType;
import com.plateer.ec1.order.strategy.AfterStrategy;
import com.plateer.ec1.order.strategy.DataStrategy;
import com.plateer.ec1.order.vo.OrderDto;
import com.plateer.ec1.order.vo.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderContext orderContext;
    private final Map<OrderType, DataStrategy> dataStrategyMap = new HashMap<>();
    private final Map<SystemType, AfterStrategy> afterStrategyMap = new HashMap<>();

    public OrderServiceImpl(OrderContext orderContext, List<DataStrategy> dataStrategies, List<AfterStrategy> afterStrategies) {
        this.orderContext = orderContext;
        dataStrategies.forEach(c -> dataStrategyMap.put(c.getType(), c));
        afterStrategies.forEach(c -> afterStrategyMap.put(c.getType(), c));
    }

    @Override
    public OrderDto order(OrderRequest orderRequest) {
        DataStrategy dataStrategy = getDataStrategy(orderRequest);
        AfterStrategy afterStrategy = getAfterStrategy(orderRequest);
        log.info("----------- DataStrategy :" + dataStrategy);
        log.info("----------- AfterStrategy :" +afterStrategy);
        return orderContext.excute(dataStrategy, afterStrategy, orderRequest);
    }

    private DataStrategy getDataStrategy(OrderRequest orderRequest) {
        return dataStrategyMap.get(orderRequest.getOrderType());
    }

    private AfterStrategy getAfterStrategy(OrderRequest orderRequest) {
        return afterStrategyMap.get(orderRequest.getSystemType());
    }

}
