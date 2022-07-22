package com.plateer.ec1.promotion.factory;

import com.plateer.ec1.promotion.enums.PromotionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class CalculationFactory {

    private final Map<PromotionType, Calculation> calculatorMap = new HashMap<>();

    public CalculationFactory(List<Calculation> calculators) {
        calculators.forEach(c -> this.calculatorMap.put(c.getType(), c));
    }

    public Calculation getPromotionCalculator(PromotionType type) {
        return calculatorMap.get(type);
    }
}
