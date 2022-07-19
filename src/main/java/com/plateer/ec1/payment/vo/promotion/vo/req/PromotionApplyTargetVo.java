package com.plateer.ec1.payment.vo.promotion.vo.req;

import com.plateer.ec1.common.code.promotion.PRM0001Enum;
import com.plateer.ec1.common.code.promotion.PRM0004Enum;
import com.plateer.ec1.common.code.promotion.PRM0012Enum;
import com.plateer.ec1.payment.vo.promotion.vo.Product;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PromotionApplyTargetVo {
    private String prmKindCd;   //PRM0001 프로모션 종류코드 (가격조정, 쿠폰)
    private String cpnKindCd;   //PRM0004 쿠폰종류코드 (상품, 중복, 장바구니)
    private String degrCcd;     //PRM0012 차수구분코드 (1,2,3,4차)
    private String mbrNo;
    private List<Product> productList;

    public static PromotionApplyTargetVo ofCouponInfo(RequestPromotionVo vo) {
        return PromotionApplyTargetVo.builder()
                .prmKindCd(PRM0001Enum.COUPON.getCode())
                .cpnKindCd(PRM0004Enum.PRODUCT_COUPON.getCode())
                .degrCcd(PRM0012Enum.ONE.getCode())
                .mbrNo(vo.getMbrNo())
                .productList(vo.getProductList())
                .build();
    }
    public static PromotionApplyTargetVo ofCartInfo(RequestPromotionVo vo) {
        return PromotionApplyTargetVo.builder()
                .prmKindCd(PRM0001Enum.COUPON.getCode())
                .cpnKindCd(PRM0004Enum.CART_COUPON.getCode())
                .degrCcd(PRM0012Enum.THREE.getCode())
                .mbrNo(vo.getMbrNo())
                .productList(vo.getProductList())
                .build();
    }


}
