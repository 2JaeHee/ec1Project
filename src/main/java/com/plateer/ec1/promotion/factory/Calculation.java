package com.plateer.ec1.promotion.factory;

import com.plateer.ec1.promotion.vo.req.RequestPromotionVo;
import com.plateer.ec1.promotion.vo.res.ResponseBaseVo;
import com.plateer.ec1.promotion.enums.PromotionType;

public interface Calculation {
    /**
     * 프로모션 데이터 계산
     */
    ResponseBaseVo getCalculationData(RequestPromotionVo requestPromotionVo);

    PromotionType getType();
}
