package com.plateer.ec1.promotion.factory;

import com.plateer.ec1.promotion.vo.ResponseBaseVo;
import com.plateer.ec1.promotion.enums.PromotionType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CalculationFactory<T extends ResponseBaseVo> {

    private final Map<PromotionType, Calculation> calculatorMap = new HashMap<>();
    private final List<Calculation> calculators;

    public CalculationFactory(List<Calculation> calculators) {
        this.calculators = calculators;
        this.calculators.forEach(c -> this.calculatorMap.put(c.getType(), c));
    }

    public Calculation getPromotionCalculator(PromotionType type) {
        return calculatorMap.get(type);
    }
}
