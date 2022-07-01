package com.plateer.ec1.promotion.controller;

import com.plateer.ec1.promotion.enums.PromotionType;
import com.plateer.ec1.promotion.factory.Calculation;
import com.plateer.ec1.promotion.factory.CalculationFactory;
import com.plateer.ec1.promotion.service.PromotionService;
import com.plateer.ec1.promotion.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/promotion")
public class PromotionController {

    private final CalculationFactory calculationFactory;
    private final PromotionService promotionService;

    public ResponseProductCouponVo getPromotionApplyData(RequestPromotionVo requestPromotionVo, PromotionType promotionType){
        return (ResponseProductCouponVo) calculationFactory.getPromotionCalculator(promotionType)
                .getCalculationData(requestPromotionVo);
    }

    /**
     * 쿠폰 다운로드
     * @param ccCpnIssueReqVo
     */
    @PutMapping("/couponDownload")
    public void couponDownload(CcCpnIssueReqVo ccCpnIssueReqVo){
        promotionService.couponDownload(ccCpnIssueReqVo);
    }

    /**
     * 상품쿠폰 적용 (상품상세화면에서 쿠폰적용 시 보이는 목록)
     * @param requestPromotionVo
     * @return
     */
    public ResponseProductCouponVo productCoupon(RequestPromotionVo requestPromotionVo) {
        Calculation promotionCalculator = calculationFactory.getPromotionCalculator(PromotionType.PRODUCT_COUPON);
        return (ResponseProductCouponVo) promotionCalculator.getCalculationData(requestPromotionVo);
    }

    //장바구니쿠폰적용 (주문서에서 쿠폰적용 시 보이는 목록)
    public ResponseCartCouponVo cartCoupon(RequestPromotionVo requestPromotionVo) {
        Calculation promotionCalculator = calculationFactory.getPromotionCalculator(PromotionType.CART_COUPON);
        return (ResponseCartCouponVo) promotionCalculator.getCalculationData(requestPromotionVo);
    }
    //회원 별 포인트 조회 (주문서 - 포인트 영역에 노출)


    // PromotionExternalService

    //포인트사용 (결제 프로세스에서 서비스 호출)

    //포인트사용취소 (결제 프로세스에서 서비스 호출)
}
