package com.plateer.ec1.payment.vo.promotion.controller;

import com.plateer.ec1.payment.vo.promotion.enums.PromotionType;
import com.plateer.ec1.payment.vo.promotion.factory.Calculation;
import com.plateer.ec1.payment.vo.promotion.factory.CalculationFactory;
import com.plateer.ec1.payment.vo.promotion.service.PromotionService;
import com.plateer.ec1.payment.vo.promotion.vo.req.RequestCouponIssueVo;
import com.plateer.ec1.payment.vo.promotion.vo.req.RequestPromotionVo;
import com.plateer.ec1.payment.vo.promotion.vo.res.ResponseBaseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/promotion")
public class PromotionController {

    private final CalculationFactory calculationFactory;
    private final PromotionService promotionService;

    /**
     * 쿠폰 다운로드
     * @param requestCouponIssueVo
     */
    @RequestMapping(value = "/couponDownload", method = RequestMethod.PUT)
    public void couponDownload(@Valid @RequestBody RequestCouponIssueVo requestCouponIssueVo){
        promotionService.couponDownload(requestCouponIssueVo);
    }

    /**
     * 상품쿠폰 적용 (상품상세화면에서 쿠폰적용 시 보이는 목록)
     * @param  requestPromotionVo
     * @return ResponseBaseVo
     */
    @RequestMapping(value = "/productCouponApply", method = RequestMethod.POST)
    public ResponseBaseVo productCouponApply(@Valid @RequestBody RequestPromotionVo requestPromotionVo) {
        Calculation promotionCalculator = calculationFactory.getPromotionCalculator(PromotionType.PRODUCT_COUPON);

        ResponseBaseVo calculationData = promotionCalculator.getCalculationData(requestPromotionVo);
        calculationData.setMemberNo(requestPromotionVo.getMbrNo());
        return calculationData;
    }

    /**
     *  장바구니쿠폰적용 (주문서에서 쿠폰적용 시 보이는 목록)
     * @param requestPromotionVo
     * @return
     */
    @RequestMapping(value = "/cartCouponApply", method = RequestMethod.POST)
    public ResponseBaseVo cartCoupon(@RequestBody RequestPromotionVo requestPromotionVo) {
        Calculation promotionCalculator = calculationFactory.getPromotionCalculator(PromotionType.CART_COUPON);
        return promotionCalculator.getCalculationData(requestPromotionVo);
    }
}
