package com.plateer.ec1.promotion.factory.impl;

import com.plateer.ec1.common.code.promotion.PRM0003Enum;
import com.plateer.ec1.common.code.promotion.PromotionConstants;
import com.plateer.ec1.promotion.enums.PromotionType;
import com.plateer.ec1.promotion.factory.Calculation;
import com.plateer.ec1.promotion.mapper.PromotionMapper;
import com.plateer.ec1.promotion.vo.Product;
import com.plateer.ec1.promotion.vo.ProductCouponVo;
import com.plateer.ec1.promotion.vo.Promotion;
import com.plateer.ec1.promotion.vo.ResponseProductCouponVo;
import com.plateer.ec1.promotion.vo.req.ProductReq;
import com.plateer.ec1.promotion.vo.req.RequestPromotionVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
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
     * @param reqVo
     * @return
     */
    @Override
    public ResponseProductCouponVo getCalculationData(RequestPromotionVo reqVo) {
        // 프로모션 목록
        List<Promotion> promotionList = getAvailablePromotionData(reqVo);
        // 상품별 프로모션 가격설정
        List<ProductCouponVo> calculateDcAmtList = calculateDcAmt(reqVo.getProductList(), promotionList);
        //최대할인값 셋팅
        ResponseProductCouponVo responseProductCouponVo = calculateMaxBenefit(calculateDcAmtList);
        return responseProductCouponVo;
    }
    /**
     * 조회 된 프로모션 정보 계산 적용
     * @param productList
     * @param promotionList
     * @return List<ProductCouponVo>
     */
    private List<ProductCouponVo> calculateDcAmt(List<Product> productList, List<Promotion> promotionList) {
        //상품번호 - 상품정보 map 생성
        Map<String, Product> productMap = getProductMap(productList);

        //프로모션 할인가격 setting
        List<Promotion> collect = promotionList.stream().map(vo -> {
            Product product = productMap.get(vo.getAplyTgtNo());
            vo.setApplyPrmYn(product.getPrmNo());
            vo.calculateAmtDiscount(product);
            return vo;
        }).collect(Collectors.toList());

        //상품번호 기준으로 프로모션 그룹핑
        Map<String, List<Promotion>> promotionMap = promotionList.stream().collect(Collectors.groupingBy(Promotion::getAplyTgtNo));

        //List<ProductCouponVo>
        return  promotionMap.entrySet().stream().map(vo -> {
            Product product = productMap.get(vo.getKey());
            return ProductCouponVo.of(product, vo.getValue());
        }).collect(Collectors.toList());
    }
    /**
     * 최대할인계산
     * @param productCouponVoList
     * @return
     */
    private ResponseProductCouponVo calculateMaxBenefit(List<ProductCouponVo> productCouponVoList) {
        for(ProductCouponVo vo: productCouponVoList){
            calculate(vo.getPromotionList());
        }
        return ResponseProductCouponVo.of(productCouponVoList);
    }

    /**
     * 최대할인값 셋팅
     * @param promotionList
     */
    private void calculate(List<Promotion> promotionList){
        Optional<Promotion> maxPromotionResVo = promotionList.stream().max(Comparator.comparing(Promotion::getCalculateDcAmt));

        maxPromotionResVo.ifPresent(availablePromotionResVo -> promotionList.stream().map(vo -> {
            String maxBenefitYn = PromotionConstants.N;
            if (maxPromotionResVo.get().getPrmNo().equals(vo.getPrmNo())) {
                maxBenefitYn = PromotionConstants.Y;
            }
            vo.setMaxBenefitYn(maxBenefitYn);   //benefit setter
            return vo;
        }).collect(Collectors.toList()));
    }

    /**
     * 상품 쿠폰 정보 조회
     * @param requestPromotionVo
     * @return Promotion
     */
    private List<Promotion> getAvailablePromotionData(RequestPromotionVo requestPromotionVo){
        return promotionMapper.getPrmAplyTgtList(requestPromotionVo);
    }
    /**
     * 상품 List -> 상품 번호 기준으로 Map으로 변환
     * @param productList
     * @return
     */
    private Map<String, Product> getProductMap(List<Product> productList){
        return productList.stream().collect(Collectors.toMap(Product::getGoodsNo, vo -> vo));
    }
}
