package com.plateer.ec1.promotion.factory.impl;

import com.plateer.ec1.common.utils.CollectionUtil;
import com.plateer.ec1.promotion.enums.PromotionType;
import com.plateer.ec1.promotion.factory.Calculation;
import com.plateer.ec1.promotion.mapper.PromotionMapper;
import com.plateer.ec1.promotion.vo.CartCouponVo;
import com.plateer.ec1.promotion.vo.Product;
import com.plateer.ec1.promotion.vo.Promotion;
import com.plateer.ec1.promotion.vo.req.PromotionApplyTargetVo;
import com.plateer.ec1.promotion.vo.req.RequestPromotionVo;
import com.plateer.ec1.promotion.vo.res.ResponseCartCouponVo;
import com.plateer.ec1.product.mapper.ProductMapper;
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
        // 프로모션 기준으로 상품 맵핑
        List<CartCouponVo> productMappingList = productMapping(reqVo.getProductList(), promotionList);
        // 조회 된 프로모션 정보 계산 적용
        List<CartCouponVo> calculateDcAmtList = calculateDcAmt(productMappingList);
        //최대할인값 셋팅
        List<CartCouponVo> benefitPromotionList = calculateMaxBenefit(calculateDcAmtList);

        return ResponseCartCouponVo.of(benefitPromotionList);
    }

    private List<CartCouponVo> productMapping(List<Product> reqProductList, List<Promotion> promotionList) {
        //parameter 상품번호별 상품 그룹핑
        Map<String, List<Product>> reqProductGruping = reqProductList.stream().collect(Collectors.groupingBy(Product::getGoodsNo));

        //적용 가능한 프로모션별 그룹핑 (IssueNo별 상품번호 조회를 위해)
        Map<Long, List<Promotion>> promotionGruping = promotionList.stream().collect(Collectors.groupingBy(Promotion::getCpnIssNo));

        // 프로모션 List
        List<Promotion> filterPromotionList = promotionList.stream().filter(CollectionUtil.distinctByKey(Promotion::getCpnIssNo)).collect(Collectors.toList());

        //TODO 개선필요
        return filterPromotionList.stream().map(vo -> {
            List<Promotion> promotions = promotionGruping.get(vo.getCpnIssNo());    //IssueNo별 상품번호f리스트 (select data)
            List<Product> productList = new ArrayList<>();
            reqProductList.stream().map(reqVo -> {
                for (Promotion promotion : promotions) {
                    if (reqVo.getGoodsNo().equals(promotion.getAplyTgtNo())) {
                        productList.add(reqVo);
                    }
                }
                return productList;
            }).collect(Collectors.toList());
            return CartCouponVo.of(vo, productList);
        }).collect(Collectors.toList());
    }
    /**
     * 조회 된 프로모션 정보 계산 적용
     *
     * @return ResponseCartCouponVo
     */
    private List<CartCouponVo> calculateDcAmt(List<CartCouponVo> productList) {
        List<CartCouponVo> calculateList = productList.stream().map(prd -> {
            Promotion promotion = prd.getPromotion();
            prd.getProductList().stream().map(vo -> {
                vo.calculateAmtDiscount(promotion);
                return vo;
            }).collect(Collectors.toList());
            return prd;
        }).collect(Collectors.toList());

        //프로모션 할인금액 계산
       return calculateList.stream().map(vo -> {
            double sum = vo.getProductList().stream().mapToDouble(Product::getCalculateDcAmt).sum();    //총 할인금액
           Promotion promotion = vo.getPromotion();
           promotion.setCalculateDcAmt(promotion.getCalculateDcAmt(sum));
            return vo;
        }).collect(Collectors.toList());
    }
    /**
     * 최대할인계산
     * @param cartCouponVoList
     * @return
     */
    private List<CartCouponVo> calculateMaxBenefit(List<CartCouponVo> cartCouponVoList) {
        Optional<CartCouponVo> maxPromotionResVo = cartCouponVoList.stream().max(Comparator.comparing(vo -> vo.getPromotion().getCalculateDcAmt()));

        List<CartCouponVo> collect = new ArrayList<>();
        if (maxPromotionResVo.isPresent()) {
            collect = cartCouponVoList.stream().map(vo -> {
                vo.getPromotion().setMaxBenefitYn(maxPromotionResVo.get().getPromotion().getPrmNo());
                return vo;
            }).collect(Collectors.toList());
        }
        return collect;
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
