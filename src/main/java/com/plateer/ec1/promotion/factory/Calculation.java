package com.plateer.ec1.promotion.factory;

import com.plateer.ec1.promotion.vo.RequestPromotionVo;
import com.plateer.ec1.promotion.vo.ResponseBaseVo;
import com.plateer.ec1.promotion.enums.PromotionType;

public interface Calculation<T extends ResponseBaseVo> {
    /**
     * 프로모션 데이터 계산
     */
    T getCalculationData(RequestPromotionVo requestPromotionVo);

    PromotionType getType();
}
