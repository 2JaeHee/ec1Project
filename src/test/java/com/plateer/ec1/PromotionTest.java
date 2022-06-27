package com.plateer.ec1;

import com.plateer.ec1.common.model.promotion.CcCpnBaseModel;
import com.plateer.ec1.common.model.promotion.CcCpnIssueModel;
import com.plateer.ec1.promotion.controller.PromotionController;
import com.plateer.ec1.promotion.enums.PromotionType;
import com.plateer.ec1.promotion.factory.CalculationFactory;
import com.plateer.ec1.promotion.vo.RequestPromotionVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PromotionTest {
    @Autowired
    private PromotionController promotionController;
    @Autowired
    private CalculationFactory calculationFactory;

    // 회원번호(테스트를 위해 고정값 사용)
    private final String mbrNo = "test01";
    
    @Test
    @DisplayName("사용가능한 쿠폰목록 조회")
    void getAvailableCouponList(){
        RequestPromotionVo requestPromotionVo = new RequestPromotionVo();
        requestPromotionVo.setMbrNo(mbrNo);
        promotionController.getAvailableCouponList(requestPromotionVo);
    }

    @Test
    @DisplayName("쿠폰 다운로드")
    void saveCouponDownload(){
        // 회원정보
        RequestPromotionVo requestPromotionVo = new RequestPromotionVo();
        requestPromotionVo.setMbrNo(mbrNo);
        List<CcCpnBaseModel> getAvailableCouponList = promotionController.getAvailableCouponList(requestPromotionVo);
        // 선택한 쿠폰 (테스트를 위해 0번째꺼 선택해놈)
        CcCpnBaseModel ccCpnBaseModel = getAvailableCouponList.get(0);

        CcCpnIssueModel ccCpnIssueModel = CcCpnIssueModel.builder().build();
        ccCpnIssueModel.setMbrNo(mbrNo);
        ccCpnIssueModel.setPrmNo(ccCpnBaseModel.getPrmNo());

        promotionController.saveCouponDownload(ccCpnIssueModel);
    }
    @Test
    void priceDiscount(){
        RequestPromotionVo reqVo = new RequestPromotionVo();

        calculationFactory.getPromotionCalculator(PromotionType.PRICE_DISCOUNT)
                .getCalculationData(reqVo);
    }

    @Test
    void productCoupon(){
        RequestPromotionVo reqVo = new RequestPromotionVo();

        calculationFactory.getPromotionCalculator(PromotionType.PRODUCT_COUPON)
                .getCalculationData(reqVo);
    }

    @Test
    void cartCoupon(){
        RequestPromotionVo reqVo = new RequestPromotionVo();

        calculationFactory.getPromotionCalculator(PromotionType.CART_COUPON)
                .getCalculationData(reqVo);
    }
    @Test
    void test(){
        //상품쿠폰
        RequestPromotionVo requestPromotionVo = new RequestPromotionVo();
        promotionController.getPromotionApplyData(requestPromotionVo, PromotionType.PRODUCT_COUPON);
    }

}
