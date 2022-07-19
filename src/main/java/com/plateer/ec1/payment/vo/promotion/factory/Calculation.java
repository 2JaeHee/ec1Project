package com.plateer.ec1.payment.vo.promotion.factory;

import com.plateer.ec1.payment.vo.promotion.vo.req.RequestPromotionVo;
import com.plateer.ec1.payment.vo.promotion.vo.res.ResponseBaseVo;
import com.plateer.ec1.payment.vo.promotion.enums.PromotionType;

public interface Calculation {
    /**
     * 프로모션 데이터 계산
     */
    ResponseBaseVo getCalculationData(RequestPromotionVo requestPromotionVo);

    PromotionType getType();
}
