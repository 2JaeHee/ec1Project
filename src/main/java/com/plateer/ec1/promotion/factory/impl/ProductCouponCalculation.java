package com.plateer.ec1.promotion.factory.impl;

import com.plateer.ec1.common.code.promotion.PromotionConstants;
import com.plateer.ec1.common.model.promotion.CcPrmBaseModel;
import com.plateer.ec1.product.mapper.ProductMapper;
import com.plateer.ec1.promotion.enums.PromotionType;
import com.plateer.ec1.promotion.factory.Calculation;
import com.plateer.ec1.promotion.mapper.PromotionMapper;
import com.plateer.ec1.promotion.vo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductCouponCalculation implements Calculation {
    private final PromotionMapper promotionMapper;
    private final ProductMapper productMapper;

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
        List<AvailablePromotionResVo> promotionList = getAvailablePromotionData(reqVo);

        // 상품별 프로모션 가격설정
        List<ProductCouponVo> productCouponVoList = calculateDcAmt(reqVo, promotionList);
        ResponseProductCouponVo responseProductCouponVo = calculateMaxBenefit(productCouponVoList);

//        }
        return responseProductCouponVo;
    }
    /**
     * 조회 된 프로모션 정보 계산 적용
     * @param promotionList
     * @return ResponseBaseVo
     */
    private List<ProductCouponVo> calculateDcAmt(RequestPromotionVo reqVo, List<AvailablePromotionResVo> promotionList) {
        Map<String, List<AvailablePromotionResVo>> promotionMap = promotionList.stream().collect(Collectors.groupingBy(AvailablePromotionResVo::getAplyTgtNo));
        List<Product> productList = productMapper.getGoodsBaseInfo(reqVo.getProductList());
        Map<String, Product> productMap = productList.stream().collect(Collectors.toMap(Product::getGoodsNo, vo -> vo));

        List<ProductCouponVo> productCouponVoList = promotionMap.entrySet().stream().map(vo -> {
            Product product = productMap.get(vo.getKey());
            return ProductCouponVo.of(product, vo.getValue());
        }).collect(Collectors.toList());

        productCouponVoList.stream().map(vo -> {
            Product product = vo.getProduct();
            vo.getPromotionList().stream().map(promotion -> {
                promotion.setMaxDcAmt(product.getPrc());
                return promotion;
            });
            return true;
        }).collect(Collectors.toList());

        return productCouponVoList;
    }


    /**
     * 최대할인계산
     * @param productCouponVoList
     * @return
     */
    private ResponseProductCouponVo calculateMaxBenefit(List<ProductCouponVo> productCouponVoList) {
        log.info("[ProductCouponCalculation.calculateMaxBenefit]");

        productCouponVoList.stream().map(vo -> {
            return calculate(vo.getPromotionList());
        }).collect(Collectors.toList());

        //최대할인계산
        log.info("상품 쿠폰 최대 할인 계산");
        return ResponseProductCouponVo.of(productCouponVoList);
    }

    private List<AvailablePromotionResVo> calculate(List<AvailablePromotionResVo> availablePromotionResVos){
        Optional<AvailablePromotionResVo> availablePromotionResVo = availablePromotionResVos.stream().min(Comparator.comparing(AvailablePromotionResVo::getCalculateDcAmt));
        if(availablePromotionResVo.isPresent()){
            availablePromotionResVos.stream().map(vo -> {
                String maxBenefitYn = PromotionConstants.N;
                if(availablePromotionResVo.get().getAplyTgtNo().equals(vo.getAplyTgtNo())){
                    maxBenefitYn = PromotionConstants.Y;
                }
                vo.setMaxBenefitYn(maxBenefitYn);
                return vo;
            }).collect(Collectors.toList());
        }
        return availablePromotionResVos;
    }

    /**
     * 상품 쿠폰 정보 조회
     * @param requestPromotionVo
     * @return Promotion
     */
    private List<AvailablePromotionResVo> getAvailablePromotionData(RequestPromotionVo requestPromotionVo){
        //적용 가능한 프로모션 정보 조회 (필수 : 상품번호 목록, 회원번호)
        return promotionMapper.getPrmAplyTgtList(requestPromotionVo);
    }
}
