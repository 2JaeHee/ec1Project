package com.plateer.ec1.promotion;

import com.plateer.ec1.common.model.promotion.CcCpnBaseModel;
import com.plateer.ec1.common.model.promotion.CcCpnIssueModel;
import com.plateer.ec1.promotion.controller.PromotionController;
import com.plateer.ec1.promotion.enums.PromotionType;
import com.plateer.ec1.promotion.factory.CalculationFactory;
import com.plateer.ec1.promotion.vo.RequestPromotionVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PromotionTest {
    @Autowired
    private PromotionController promotionController;
    @Autowired
    private CalculationFactory calculationFactory;

    @Test
    void priceDiscount(){
        RequestPromotionVo reqVo = new RequestPromotionVo();

        calculationFactory.getPromotionCalculator(PromotionType.PRICE_DISCOUNT)
                .getCalculationData(reqVo);
    }

    @Test
    void productCoupon(){
        RequestPromotionVo reqVo = new RequestPromotionVo();

        calculationFactory.getPromotionCalculator(PromotionType.PRODUCT_COUPON)
                .getCalculationData(reqVo);
    }

    @Test
    void cartCoupon(){
        RequestPromotionVo reqVo = new RequestPromotionVo();

        calculationFactory.getPromotionCalculator(PromotionType.CART_COUPON)
                .getCalculationData(reqVo);
    }
    @Test
    void test(){
        //상품쿠폰
        RequestPromotionVo requestPromotionVo = new RequestPromotionVo();
        promotionController.getPromotionApplyData(requestPromotionVo, PromotionType.PRODUCT_COUPON);
    }

}
