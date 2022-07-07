package com.plateer.ec1.promotion.factory.impl;

import com.plateer.ec1.common.utils.CollectionUtil;
import com.plateer.ec1.promotion.enums.PromotionType;
import com.plateer.ec1.promotion.factory.Calculation;
import com.plateer.ec1.promotion.mapper.PromotionMapper;
import com.plateer.ec1.promotion.vo.Product;
import com.plateer.ec1.promotion.vo.ProductCouponVo;
import com.plateer.ec1.promotion.vo.Promotion;
import com.plateer.ec1.promotion.vo.req.PromotionApplyTargetVo;
import com.plateer.ec1.promotion.vo.req.RequestPromotionVo;
import com.plateer.ec1.promotion.vo.res.ResponseProductCouponVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductCouponCalculation implements Calculation {
    private final PromotionMapper promotionMapper;

    @Override
    public PromotionType getType() {
        return PromotionType.PRODUCT_COUPON;
    }
    /**
     * 상품쿠폰 조회 (상품에 적용되는 쿠폰 프로모션)
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseProductCouponVo getCalculationData(RequestPromotionVo reqVo) {
        // 적용 가능한 프로모션 목록 (1차쿠폰만 조회)
        List<Promotion> promotionList = getAvailablePromotionData(reqVo);
        // 조회 된 프로모션 정보 계산 적용
        List<ProductCouponVo> calculateDcAmtList = calculateDcAmt(reqVo.getProductList(), promotionList);
        //최대할인값 셋팅
        List<ProductCouponVo> benefitPromotionList = calculateMaxBenefit(calculateDcAmtList);

        return ResponseProductCouponVo.of(benefitPromotionList);
    }
    /**
     * 조회 된 프로모션 정보 계산 적용
     */
    private List<ProductCouponVo> calculateDcAmt(List<Product> productList, List<Promotion> promotionList) {
        //상품번호 - 상품정보 map 생성
        Map<String, Product> productMap = getProductMap(productList);

        //프로모션 할인가격, 기적용프로모션 setting
        promotionList.stream().map(vo -> {
            Product product = productMap.get(vo.getAplyTgtNo());
            //vo.setApplyPrmYn(product.getPrmNo(), product.getCpnIssNo());
            vo.calculateAmtDiscount(product);
            return vo;
        }).collect(Collectors.toList());

        return promotionMapping(productList, promotionList);
    }

    /**
     * 상품번호 기준으로 프로모션 그룹핑
     * @param productList
     * @param promotionList
     * @return
     */
    private List<ProductCouponVo> promotionMapping(List<Product> productList, List<Promotion> promotionList) {
        Map<String, List<Promotion>> promotionMap = promotionList.stream().collect(Collectors.groupingBy(Promotion::getAplyTgtNo));
        return productList.stream().map(vo -> {
            // 상품 최소구입금액, 상품가격 체크
            List<Promotion> filterPromotionList = promotionMap.containsKey(vo.getGoodsNo()) ? filterPromotionList(vo, promotionMap.get(vo.getGoodsNo())) : new ArrayList<>();
            return ProductCouponVo.of(vo, filterPromotionList);
        }).collect(Collectors.toList());
    }

    /**
     * 상품 별 프로모션 적용 시 최소구매금액체크
     * @param promotionList
     * @param product
     * @return
     */
    //TODO 변경예정
    private List<Promotion> filterPromotionList(Product product, List<Promotion> promotionList){
        return promotionList.stream().filter(vo -> {
            return vo.getMinPurAmt() <= product.getPrc();
        }).collect(Collectors.toList());
    }

    /**
     * 최대할인계산
     */
    private List<ProductCouponVo> calculateMaxBenefit(List<ProductCouponVo> productCouponVoList) {
        for(ProductCouponVo vo: productCouponVoList){
            calculate(vo.getPromotionList());
        }
        return productCouponVoList;
    }

    /**
     * 최대할인값 셋팅
     */
    private void calculate(List<Promotion> promotionList){
        Optional<Promotion> maxPromotionResVo = promotionList.stream().max(Comparator.comparing(Promotion::getCalculateDcAmt));

        maxPromotionResVo.ifPresent(availablePromotionResVo -> promotionList.stream().map(vo -> {
            vo.setMaxBenefitYn(maxPromotionResVo.get().getPrmNo());
            return vo;
        }).collect(Collectors.toList()));
    }

    /**
     * 상품 쿠폰 정보 조회 (적용가능한 프로모션 조회)
     */
    private List<Promotion> getAvailablePromotionData(RequestPromotionVo requestPromotionVo){
        return promotionMapper.getPrmAplyTgtList(PromotionApplyTargetVo.ofCouponInfo(requestPromotionVo));
    }

    /**
     * 상품 List -> 상품 번호 기준으로 Map으로 변환
     */
    private Map<String, Product> getProductMap(List<Product> productList){
        return productList.stream()
                .filter(CollectionUtil.distinctByKey(Product::getGoodsNo))
                .collect(Collectors.toMap(Product::getGoodsNo, vo -> vo));
    }
}
