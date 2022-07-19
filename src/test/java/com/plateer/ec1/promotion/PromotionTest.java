package com.plateer.ec1.promotion;

import com.plateer.ec1.payment.vo.promotion.controller.PromotionController;
import com.plateer.ec1.payment.vo.promotion.enums.PromotionType;
import com.plateer.ec1.payment.vo.promotion.factory.CalculationFactory;
import com.plateer.ec1.payment.vo.promotion.vo.req.RequestPromotionVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PromotionTest {
    @Autowired
    private PromotionController promotionController;
    @Autowired
    private CalculationFactory calculationFactory;

    @Test
    void productCoupon(){
        RequestPromotionVo reqVo = RequestPromotionVo.builder().build();

        calculationFactory.getPromotionCalculator(PromotionType.PRODUCT_COUPON)
                .getCalculationData(reqVo);
    }

    @Test
    void cartCoupon(){
        RequestPromotionVo reqVo = RequestPromotionVo.builder().build();

        calculationFactory.getPromotionCalculator(PromotionType.CART_COUPON)
                .getCalculationData(reqVo);
    }
}
