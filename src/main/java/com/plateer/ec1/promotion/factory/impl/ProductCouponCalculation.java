package com.plateer.ec1.promotion.factory.impl;

import com.plateer.ec1.promotion.vo.Promotion;
import com.plateer.ec1.promotion.vo.RequestPromotionVo;
import com.plateer.ec1.promotion.vo.ResponseProductCouponVo;
import com.plateer.ec1.promotion.enums.PromotionType;
import com.plateer.ec1.promotion.factory.Calculation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductCouponCalculation implements Calculation {
    /**
     * 상품쿠폰 조회 (상품에 적용되는 쿠폰 프로모션)
     * @param requestPromotionVo
     * @return
     */
    @Override
    public ResponseProductCouponVo getCalculationData(RequestPromotionVo requestPromotionVo) {
        Promotion promotion = getAvailablePromotionData(requestPromotionVo);

        ResponseProductCouponVo responseProductCouponVo = calculateMaxBenefit(calculateDcAmt(promotion));

        return responseProductCouponVo;
    }

    @Override
    public PromotionType getType() {
        return PromotionType.PRODUCT_COUPON;
    }

    /**
     * 최대할인계산
     * @param responseProductCouponVo
     * @return
     */
    private ResponseProductCouponVo calculateMaxBenefit(ResponseProductCouponVo responseProductCouponVo) {
        log.info("[ProductCouponCalculation.calculateMaxBenefit]");
        //최대할인계산
        log.info("상품 쿠폰 최대 할인 계산");
        return new ResponseProductCouponVo();
    }
    /**
     * 조회 된 프로모션 정보 계산 적용
     * @param promotion
     * @return ResponseBaseVo
     */
    private ResponseProductCouponVo calculateDcAmt(Promotion promotion) {
        log.info("[ProductCouponCalculation.calculateDcAmt]");
        //조회 한 쿠폰 정보로 상품 적용 후 가격 계산
        log.info("조회 한 쿠폰 정보로 상품 적용 후 가격 계산");
        return new ResponseProductCouponVo();
    }
    /**
     * 상품 쿠폰 정보 조회
     * @param requestPromotionVo
     * @return Promotion
     */
    private Promotion getAvailablePromotionData(RequestPromotionVo requestPromotionVo){
        log.info("[ProductCouponCalculation.getAvailablePromotionData]");
        //상품 쿠폰 정보 조회
        log.info("상품 쿠폰 정보 조회");
        return new Promotion();
    }
}
