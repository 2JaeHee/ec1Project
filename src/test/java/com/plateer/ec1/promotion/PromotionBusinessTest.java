package com.plateer.ec1.promotion;

import com.plateer.ec1.common.model.promotion.CcCpnIssueModel;
import com.plateer.ec1.payment.vo.promotion.controller.PromotionController;
import com.plateer.ec1.payment.vo.promotion.factory.impl.CartCouponCalculation;
import com.plateer.ec1.payment.vo.promotion.service.PromotionExternalService;
import com.plateer.ec1.payment.vo.promotion.vo.Product;
import com.plateer.ec1.payment.vo.promotion.vo.req.RequestCouponIssueVo;
import com.plateer.ec1.payment.vo.promotion.vo.req.RequestPromotionVo;
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
    private final String mbrNo = "test02";

    @Test
    @DisplayName("쿠폰 다운로드")
    void couponDownload() {
        // 회원정보
        RequestCouponIssueVo ccCpnIssueReqVo = RequestCouponIssueVo.builder()
                .prmNo(8L)
                .mbrNo(mbrNo)
                .build();

        promotionController.couponDownload(ccCpnIssueReqVo);
        
        //필수값
        //경계값 vo 테스트 사용하면 좋다
        //테스트케이스
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
        RequestPromotionVo requestPromotionVo = setParameter();
        promotionController.productCouponApply(requestPromotionVo);
    }

    @Test
    @DisplayName("장바구니 쿠폰 적용")
    void cartCoupon() {
        RequestPromotionVo requestPromotionVo = setParameter();
        promotionController.cartCoupon(requestPromotionVo);
    }

    private RequestPromotionVo setParameter(){
        List<Product> productList = new ArrayList<>();
        Product product1 = Product.builder().goodsNo("P001").itemNo("1").prc(29000L).ordCnt(2).build();
        Product product2 = Product.builder().goodsNo("P001").itemNo("2").prc(29000L).ordCnt(2).prmNo(1L).cpnIssNo(4L).build();
        Product product3 = Product.builder().goodsNo("P002").itemNo("1").prc(10250L).ordCnt(1).build();
//        Product product4 = Product.builder().goodsNo("P002").itemNo("2").prc(10250L).ordCnt(1).build();
//        Product product5 = Product.builder().goodsNo("P005").itemNo("1").prc(9000L).ordCnt(1).build();
//        Product product6 = Product.builder().goodsNo("P005").itemNo("2").prc(9000L).ordCnt(1).build();
//        Product product7 = Product.builder().goodsNo("P005").itemNo("3").prc(9000L).ordCnt(1).build();
//        Product product8 = Product.builder().goodsNo("P007").itemNo("1").prc(24000L).ordCnt(1).build();
//        Product product9 = Product.builder().goodsNo("P007").itemNo("2").prc(24000L).ordCnt(1).build();
//        Product product10 = Product.builder().goodsNo("P007").itemNo("3").prc(24000L).ordCnt(1).build();

        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
//        productList.add(product4);
//        productList.add(product5);
//        productList.add(product6);
//        productList.add(product7);
//        productList.add(product8);
//        productList.add(product9);
//        productList.add(product10);

        return RequestPromotionVo.builder().mbrNo(mbrNo).productList(productList).build();
    }

    private void temp(){
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
                .goodsNo("P001")
                .itemNo("2")
                .prc(29000L)
                .ordCnt(2)
                .build();


        Product product3 = Product.builder()
                .goodsNo("P002")
                .itemNo("1")
                .prc(10250L)
                .ordCnt(2)
                .build();

        Product produc4 = Product.builder()
                .goodsNo("P002")
                .itemNo("2")
                .prc(10250L)
                .ordCnt(1)
                .build();

        productList.add(product1);
        productList.add(product2);

        RequestPromotionVo requestPromotionVo = RequestPromotionVo.builder()
                .mbrNo(mbrNo)
                .productList(productList)
                .build();
    }
}
