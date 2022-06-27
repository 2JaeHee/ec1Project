package com.plateer.ec1.promotion.controller;

import com.plateer.ec1.common.code.promotion.PromotionConstants;
import com.plateer.ec1.common.model.promotion.CcCpnBaseModel;
import com.plateer.ec1.common.model.promotion.CcCpnIssueModel;
import com.plateer.ec1.promotion.service.PromotionService;
import com.plateer.ec1.promotion.vo.RequestPromotionVo;
import com.plateer.ec1.promotion.vo.ResponseProductCouponVo;
import com.plateer.ec1.promotion.enums.PromotionType;
import com.plateer.ec1.promotion.factory.CalculationFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
     * 다운로드 가능한 쿠폰 목록 조회
     * @return List<CcCpnBaseModel>
     */
    @PostMapping("/getAvailableCouponList")
    public List<CcCpnBaseModel> getAvailableCouponList(RequestPromotionVo requestPromotionVo){
        return promotionService.getAvailableCouponList(requestPromotionVo);
    }

    /**
     * 쿠폰 다운로드
     * @param ccCpnIssueModel
     */
    @PutMapping("/saveCouponDownload")
    public void saveCouponDownload(CcCpnIssueModel ccCpnIssueModel){
        System.out.println("ccCpnIssueModel = " + ccCpnIssueModel);
        ccCpnIssueModel.setPrmNo(Long.valueOf(1));
        ccCpnIssueModel.setMbrNo("test01");
        promotionService.saveCouponDownload(ccCpnIssueModel);
    }

    //회원 별 포인트 조회 (주문서 - 포인트 영역에 노출)

    //다운로드 가능한 쿠폰조회 (다운로드목록)

    //쿠폰다운로드 (쿠폰발급회원)

    //상품쿠폰적용 (상품상세화면에서 쿠폰적용 시 보이는 목록)

    //장바구니쿠폰적용 (주문서에서 쿠폰적용 시 보이는 목록)

    // PromotionExternalService
    // call test 후 controller에서 삭제
    //쿠폰사용 (주문 프로세스에서 서비스 호출)

    //쿠폰사용취소 (클레임 프로세스에서? 서비스 호출)

    //포인트사용 (결제 프로세스에서 서비스 호출)

    //포인트사용취소 (결제 프로세스에서 서비스 호출)
}
