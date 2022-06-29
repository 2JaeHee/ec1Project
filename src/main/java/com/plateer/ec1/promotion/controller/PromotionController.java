package com.plateer.ec1.promotion.controller;

import com.plateer.ec1.common.model.promotion.CcCpnBaseModel;
import com.plateer.ec1.common.model.promotion.CcCpnIssueModel;
import com.plateer.ec1.promotion.enums.PromotionType;
import com.plateer.ec1.promotion.factory.CalculationFactory;
import com.plateer.ec1.promotion.service.PromotionService;
import com.plateer.ec1.promotion.vo.CcCpnIssueReqVo;
import com.plateer.ec1.promotion.vo.RequestPromotionVo;
import com.plateer.ec1.promotion.vo.ResponseProductCouponVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/promotion")
public class PromotionController {

    private final CalculationFactory calculationFactory;
    private final PromotionService promotionService;

    public ResponseProductCouponVo getPromotionApplyData(RequestPromotionVo requestPromotionVo, PromotionType promotionType){
        return (ResponseProductCouponVo) calculationFactory.getPromotionCalculator(promotionType)
                .getCalculationData(requestPromotionVo);
    }

    /**
     * 쿠폰 다운로드
     * @param ccCpnIssueReqVo
     */
    @PutMapping("/couponDownload")
    public void couponDownload(CcCpnIssueReqVo ccCpnIssueReqVo){
        promotionService.couponDownload(ccCpnIssueReqVo);
    }

    //회원 별 포인트 조회 (주문서 - 포인트 영역에 노출)

    //쿠폰다운로드 (쿠폰발급회원)

    //상품쿠폰적용 (상품상세화면에서 쿠폰적용 시 보이는 목록)

    //장바구니쿠폰적용 (주문서에서 쿠폰적용 시 보이는 목록)

    // PromotionExternalService

    //포인트사용 (결제 프로세스에서 서비스 호출)

    //포인트사용취소 (결제 프로세스에서 서비스 호출)
}
