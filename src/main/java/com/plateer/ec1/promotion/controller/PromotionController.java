package com.plateer.ec1.promotion.controller;

import com.plateer.ec1.promotion.vo.RequestPromotionVo;
import com.plateer.ec1.promotion.vo.ResponseProductCouponVo;
import com.plateer.ec1.promotion.enums.PromotionType;
import com.plateer.ec1.promotion.factory.CalculationFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class PromotionController {

    private final CalculationFactory calculationFactory;

    public ResponseProductCouponVo getPromotionApplyData(RequestPromotionVo requestPromotionVo, PromotionType promotionType){
        return (ResponseProductCouponVo) calculationFactory.getPromotionCalculator(promotionType)
                .getCalculationData(requestPromotionVo);
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
