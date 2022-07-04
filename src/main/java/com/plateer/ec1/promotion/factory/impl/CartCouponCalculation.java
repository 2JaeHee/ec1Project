package com.plateer.ec1.promotion.factory.impl;

import com.plateer.ec1.common.code.promotion.PromotionConstants;
import com.plateer.ec1.product.mapper.ProductMapper;
import com.plateer.ec1.promotion.mapper.PromotionMapper;
import com.plateer.ec1.promotion.vo.*;
import com.plateer.ec1.promotion.enums.PromotionType;
import com.plateer.ec1.promotion.factory.Calculation;
import com.plateer.ec1.promotion.vo.req.RequestPromotionVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartCouponCalculation implements Calculation {
    private final PromotionMapper promotionMapper;
    private final ProductMapper productMapper;

    @Override
    public PromotionType getType() {
        return PromotionType.CART_COUPON;
    }
    /**
     * 장바구니 쿠폰 조회 (주문서에서 적용되는 쿠폰 프로모션)
     * @param reqVo
     * @return
     */
    @Override
    public ResponseCartCouponVo getCalculationData(RequestPromotionVo reqVo) {
        // 상품 목록 조회
        List<Product> productList = productMapper.getGoodsBaseInfo(reqVo.getProductList());
        // 프로모션 목록
        List<Promotion> promotionList = getAvailablePromotionData(reqVo);
        List<CartCouponVo> calculateDcAmtList = calculateDcAmt(productList, promotionList);

        return calculateMaxBenefit(calculateDcAmtList);
    }
    /**
     * 조회 된 프로모션 정보 계산 적용
     *
     * @param productList
     * @param promotionList
     * @return ResponseCartCouponVo
     */
    private List<CartCouponVo> calculateDcAmt(List<Product> productList, List<Promotion> promotionList) {
        Map<Long, List<Product>> productMap = productList.stream().collect(Collectors.groupingBy(Product::getPrmNo));

        List<CartCouponVo> collect = promotionList.stream().map(vo -> {
            List<Product> products = productMap.get(vo.getPrmNo());
            return CartCouponVo.of(vo, products);
        }).collect(Collectors.toList());

        return collect;
    }

    /**
     * 최대할인계산
     * @param cartCouponVoList
     */
    private ResponseCartCouponVo calculateMaxBenefit(List<CartCouponVo> cartCouponVoList) {
        Map<Promotion, Double> calculateDcAmtMap = new HashMap<>();

        for (CartCouponVo vo : cartCouponVoList) {
            double sum = vo.getProductList().stream().mapToDouble(Product::getCalculateDcAmt).sum();
            calculateDcAmtMap.put(vo.getPromotion(), sum);
        }

        Promotion maxPromotion = Collections.max(calculateDcAmtMap.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey();

        //TODO 수정예정
        List<CartCouponVo> collect = cartCouponVoList.stream()
                .filter(vo -> vo.getPromotion().equals(maxPromotion))
                .map(couponVo -> {
                    couponVo.setMaxBenefitYn(PromotionConstants.Y);
                    return couponVo;
                }).collect(Collectors.toList());

        return ResponseCartCouponVo.of(collect);
    }

    /**
     * 장바구니 쿠폰 정보 조회
     * @param requestPromotionVo
     * @return Promotion
     */
    private List<Promotion> getAvailablePromotionData(RequestPromotionVo requestPromotionVo){
        //적용 가능한 프로모션 정보 조회 (필수 : 상품번호 목록, 회원번호)
        return promotionMapper.getPrmAplyTgtListTemp(requestPromotionVo);
    }
}
