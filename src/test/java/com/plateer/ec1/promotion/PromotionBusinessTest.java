package com.plateer.ec1.promotion;

import com.plateer.ec1.common.model.promotion.CcCpnBaseModel;
import com.plateer.ec1.common.model.promotion.CcCpnIssueModel;
import com.plateer.ec1.promotion.controller.PromotionController;
import com.plateer.ec1.promotion.factory.CalculationFactory;
import com.plateer.ec1.promotion.service.PromotionExternalService;
import com.plateer.ec1.promotion.vo.CcCpnIssueReqVo;
import com.plateer.ec1.promotion.vo.RequestPromotionVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PromotionBusinessTest {
    @Autowired
    PromotionController promotionController;
    @Autowired
    PromotionExternalService promotionExternalService;

    // 회원번호(테스트를 위해 고정값 사용)
    private final String mbrNo = "test02";

    @Test
    @DisplayName("쿠폰 다운로드")
    void couponDownload() {
        // 회원정보
        CcCpnIssueReqVo ccCpnIssueReqVo = CcCpnIssueReqVo.builder()
                .prmNo(Long.valueOf(1))
                .mbrNo(mbrNo)
                .build();

        promotionController.couponDownload(ccCpnIssueReqVo);
    }

    @Test
    @DisplayName("쿠폰 사용")
    void couponUse() {
        CcCpnIssueModel ccCpnIssueModel = CcCpnIssueModel.builder().build();
        ccCpnIssueModel.setCpnIssNo(Long.valueOf(9));
        ccCpnIssueModel.setPrmNo(Long.valueOf(1));
        ccCpnIssueModel.setMbrNo(mbrNo);
        ccCpnIssueModel.setOrdNo("O22062800004");
        promotionExternalService.couponUse(ccCpnIssueModel);
    }

    @Test
    @DisplayName("쿠폰 사용 취소")
    void couponUseCancel() {
        CcCpnIssueModel ccCpnIssueModel = CcCpnIssueModel.builder().build();
        ccCpnIssueModel.setCpnIssNo(Long.valueOf(9));
        ccCpnIssueModel.setPrmNo(Long.valueOf(1));
        ccCpnIssueModel.setMbrNo(mbrNo);
        promotionExternalService.couponUseCancel(ccCpnIssueModel);
    }
}
