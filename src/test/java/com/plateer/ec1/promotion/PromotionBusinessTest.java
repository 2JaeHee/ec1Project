package com.plateer.ec1.promotion;

import com.plateer.ec1.common.model.promotion.CcCpnIssueModel;
import com.plateer.ec1.promotion.controller.PromotionController;
import com.plateer.ec1.promotion.service.PromotionExternalService;
import com.plateer.ec1.promotion.vo.req.RequestCouponIssueVo;
import com.plateer.ec1.promotion.vo.Product;
import com.plateer.ec1.promotion.vo.req.RequestPromotionVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@AutoConfigureMockMvc
public class PromotionBusinessTest {
//    @Autowired
//    private MockMvc mockMvc;

    @Autowired
    PromotionController promotionController;
    @Autowired
    PromotionExternalService promotionExternalService;

    // 회원번호(테스트를 위해 고정값 사용)
    private final String mbrNo = "test01";

    @Test
    @DisplayName("쿠폰 다운로드")
    void couponDownload() {
        // 회원정보
        RequestCouponIssueVo ccCpnIssueReqVo = RequestCouponIssueVo.builder()
                .prmNo(1L)
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
                .build();

        Product product2 = Product.builder()
                .goodsNo("P001")
                .itemNo("1")
                .prc(29000L)
                .prmNo(1L)
                .cpnIssNo(1L)
                .build();

        Product product3 = Product.builder()
                .goodsNo("P002")
                .itemNo("2")
                .prc(10250L)
                .build();

        productList.add(product1);
        productList.add(product2);
        productList.add(product3);

        RequestPromotionVo requestPromotionVo = RequestPromotionVo.builder()
                .mbrNo(mbrNo)
                .productList(productList)
                .build();
        promotionController.productCouponApply(requestPromotionVo);
    }

    @Test
    @DisplayName("장바구니 쿠폰 적용")
    void cartCoupon() {
        List<Product> productList = new ArrayList<>();
        Product product1 = Product.builder()
                .goodsNo("P001")
                .build();

        Product product2 = Product.builder()
                .goodsNo("P002")
                .build();

        productList.add(product1);
        productList.add(product2);

        RequestPromotionVo requestPromotionVo = RequestPromotionVo.builder()
                .mbrNo(mbrNo)
//                .productList(productList)
                .build();

        promotionController.cartCoupon(requestPromotionVo);
    }
}
