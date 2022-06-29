package com.plateer.ec1.promotion.service.impl;

import com.plateer.ec1.common.model.promotion.CcCpnIssueModel;
import com.plateer.ec1.common.model.promotion.CcPrmBaseModel;
import com.plateer.ec1.promotion.mapper.PromotionMapper;
import com.plateer.ec1.promotion.mapper.PromotionTrxMapper;
import com.plateer.ec1.promotion.service.PromotionExternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromotionExternalServiceImpl implements PromotionExternalService {
    private final PromotionMapper promotionMapper;
    private final PromotionTrxMapper promotionTrxMapper;
    @Override
    public void couponUse(CcCpnIssueModel ccCpnIssueModel) {
        promotionTrxMapper.modifyCouponUseInfo(ccCpnIssueModel);
    }

    @Override
    public void couponUseCancel(CcCpnIssueModel paramModel) {
        CcPrmBaseModel prmBaseInfo = promotionMapper.getPrmBaseInfo(paramModel.getPrmNo());

        //기간구분코드 10일때
        //사용취소를 위해 프로모션 기간 검증 (취소일시  < 프로모션종료일시)
        if(prmBaseInfo.periodValidate()){
            promotionTrxMapper.saveCouponRestore(paramModel);
        }
        
        //기간구분코드20일때
        //프로모션 기준일 + 쿠폰발급회원수정일
    }

}
