package com.plateer.ec1.promotion;

import com.plateer.ec1.common.model.promotion.CcCpnIssueModel;
import com.plateer.ec1.promotion.controller.PromotionController;
import com.plateer.ec1.promotion.enums.PromotionType;
import com.plateer.ec1.promotion.factory.Calculation;
import com.plateer.ec1.promotion.factory.CalculationFactory;
import com.plateer.ec1.promotion.factory.impl.CartCouponCalculation;
import com.plateer.ec1.promotion.service.PromotionExternalService;
import com.plateer.ec1.promotion.service.PromotionService;
import com.plateer.ec1.promotion.vo.Product;
import com.plateer.ec1.promotion.vo.req.RequestCouponIssueVo;
import com.plateer.ec1.promotion.vo.req.RequestPromotionVo;
import com.plateer.ec1.promotion.vo.res.ResponseBaseVo;
import com.plateer.ec1.promotion.vo.res.ResponseCartCouponVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PromotionBusinessTest {

    @Autowired
    PromotionController promotionController;
    @Autowired
    PromotionExternalService promotionExternalService;
    @Autowired
    CartCouponCalculation cartCouponCalculation;


    // 회원번호(테스트를 위해 고정값 사용)
    private final String mbrNo = "test03";

    @Test
    @DisplayName("쿠폰 다운로드")
    void couponDownload() {
        // 회원정보
        RequestCouponIssueVo ccCpnIssueReqVo = RequestCouponIssueVo.builder()
                .prmNo(7L)
                .mbrNo(mbrNo)
                .build();

        promotionController.couponDownload(ccCpnIssueReqVo);
    }

    @Test
    @DisplayName("쿠폰 사용")
    void couponUse() {
        CcCpnIssueModel ccCpnIssueModel = CcCpnIssueModel.builder()
                .cpnIssNo(9L)
                .prmNo(1L)
                .mbrNo(mbrNo)
                .ordNo("O22062800004")
                .build();

        promotionExternalService.couponUse(ccCpnIssueModel);
    }

    @Test
    @DisplayName("쿠폰 사용 취소")
    void couponUseCancel() {
        CcCpnIssueModel ccCpnIssueModel = CcCpnIssueModel.builder()
                .cpnIssNo(9L)
                .prmNo(1L)
                .mbrNo(mbrNo)
                .build();
        promotionExternalService.couponUseCancel(ccCpnIssueModel);
    }

    @Test
    @DisplayName("상품 쿠폰 적용")
    void productCoupon() {

        List<Product> productList = new ArrayList<>();
        Product product1 = Product.builder()
                .goodsNo("P001")
                .itemNo("1")
                .prc(29000L)
                .prmNo(1L)
                .cpnIssNo(1L)
                .ordCnt(2)
                .build();


        Product product2 = Product.builder()
                .goodsNo("P002")
                .itemNo("2")
                .prc(10250L)
                .ordCnt(3)
                .build();

        productList.add(product1);
        productList.add(product2);

        RequestPromotionVo requestPromotionVo = RequestPromotionVo.builder()
                .mbrNo(mbrNo)
                .productList(productList)
                .build();

        ResponseCartCouponVo res = cartCouponCalculation.getCalculationData(requestPromotionVo);
        res.getCartCouponVoList().get(0).getPromotion().getCalculateDcAmt();
    }

    @Test
    @DisplayName("장바구니 쿠폰 적용")
    void cartCoupon() {
        List<Product> productList = new ArrayList<>();
        Product product1 = Product.builder()
                .goodsNo("P001")
                .itemNo("1")
                .prc(29000L)
                .prmNo(1L)
                .cpnIssNo(1L)
                .ordCnt(2)
                .build();


        Product product2 = Product.builder()
                .goodsNo("P005")
                .itemNo("1")
                .prc(9000L)
                .ordCnt(3)
                .build();

        Product product3 = Product.builder()
                .goodsNo("P006")
                .itemNo("1")
                .prc(140000L)
                .ordCnt(3)
                .build();

        productList.add(product1);
        productList.add(product2);
        productList.add(product3);

        RequestPromotionVo requestPromotionVo = RequestPromotionVo.builder()
                .mbrNo(mbrNo)
                .productList(productList)
                .build();

        promotionController.cartCoupon(requestPromotionVo);
    }
}
