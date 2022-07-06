package com.plateer.ec1.promotion.factory.impl;

import com.plateer.ec1.common.code.promotion.PRM0004Enum;
import com.plateer.ec1.common.code.promotion.PromotionConstants;
import com.plateer.ec1.common.utils.CollectionUtil;
import com.plateer.ec1.product.mapper.ProductMapper;
import com.plateer.ec1.promotion.mapper.PromotionMapper;
import com.plateer.ec1.promotion.vo.*;
import com.plateer.ec1.promotion.enums.PromotionType;
import com.plateer.ec1.promotion.factory.Calculation;
import com.plateer.ec1.promotion.vo.req.PromotionApplyTargetVo;
import com.plateer.ec1.promotion.vo.req.RequestPromotionVo;
import com.plateer.ec1.promotion.vo.res.ResponseCartCouponVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public ResponseCartCouponVo getCalculationData(RequestPromotionVo reqVo) {
        // 적용 가능한 프로모션 목록 (3차쿠폰만 조회)
        List<Promotion> promotionList = getAvailablePromotionData(reqVo);


        // 조회 된 프로모션 정보 계산 적용
        List<CartCouponVo> calculateDcAmtList = calculateDcAmt(reqVo.getProductList(), promotionList);
        //최대할인값 셋팅
        //List<CartCouponVo> benefitPromotionList = calculateMaxBenefit(calculateDcAmtList);

        return null;
//        return ResponseCartCouponVo.of(benefitPromotionList);
    }
    /**
     * 조회 된 프로모션 정보 계산 적용
     *
     * @return ResponseCartCouponVo
     */
    private List<CartCouponVo> calculateDcAmt(List<Product> productList, List<Promotion> promotionList) {
        List<Promotion> filterPromotionList = promotionList.stream().filter(CollectionUtil.distinctByKey(Promotion::getCpnIssNo)).collect(Collectors.toList());

        Map<String, Product> collect1 = productList.stream().collect(Collectors.toMap(Product::getGoodsNo, vo -> vo));

        Map<Long, List<Promotion>> collect2 = promotionList.stream().collect(Collectors.groupingBy(Promotion::getCpnIssNo, Collectors.toList()));

//        collect2.entrySet().stream().map(vo -> {
//
//        })


        filterPromotionList.stream().map(vo -> {
            List<Product> prodctList = new ArrayList<>();

            return CartCouponVo.of(vo, prodctList);
        }).collect(Collectors.toList());


        Map<Long, List<Product>> productMap = productList.stream().collect(Collectors.groupingBy(Product::getPrmNo));


        List<CartCouponVo> collect = filterPromotionList.stream().map(vo -> {
            List<Product> products = productMap.get(vo.getPrmNo());
            return CartCouponVo.of(vo, products);
        }).collect(Collectors.toList());



        // List<Product>
        return collect;
    }

    /**
     * 최대할인계산
     * @param cartCouponVoList
     */
    private List<CartCouponVo> calculateMaxBenefit(List<CartCouponVo> cartCouponVoList) {
        Map<Promotion, Double> calculateDcAmtMap = new HashMap<>();

        for (CartCouponVo vo : cartCouponVoList) {
            double sum = vo.getProductList().stream().mapToDouble(Product::getCalculateDcAmt).sum();
            calculateDcAmtMap.put(vo.getPromotion(), sum);
        }

        Promotion maxPromotion = Collections.max(calculateDcAmtMap.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey();
        //TODO 수정예정
        return cartCouponVoList.stream()
                .filter(vo -> vo.getPromotion().equals(maxPromotion))
                .map(couponVo -> {
                    couponVo.setMaxBenefitYn(PromotionConstants.Y);
                    return couponVo;
                }).collect(Collectors.toList());

    }

    /**
     * 장바구니 쿠폰 정보 조회
     * @param requestPromotionVo
     * @return Promotion
     */
    private List<Promotion> getAvailablePromotionData(RequestPromotionVo requestPromotionVo){
        return promotionMapper.getPrmAplyTgtList(PromotionApplyTargetVo.ofCartInfo(requestPromotionVo));
    }
}
