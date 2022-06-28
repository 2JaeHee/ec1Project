package com.plateer.ec1.promotion.service.impl;

import com.plateer.ec1.common.model.promotion.CcCpnIssueModel;
import com.plateer.ec1.promotion.mapper.PromotionMapper;
import com.plateer.ec1.promotion.mapper.PromotionTrxMapper;
import com.plateer.ec1.promotion.service.PromotionExternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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
        if(getPromotionValidate(paramModel.getPrmNo())){
            CcCpnIssueModel ccCpnIssueModel = CcCpnIssueModel.builder().build();

            BeanUtils.copyProperties(paramModel, ccCpnIssueModel);
            ccCpnIssueModel.setOrgCpnIssNo(paramModel.getCpnIssNo());

            promotionTrxMapper.saveCouponRestore(ccCpnIssueModel);
        }
    }

    /**
     * 사용취소를 위해 프로모션 기간 검증 (취소일시  < 프로모션종료일시)
     * @param prmNo
     */
    private boolean getPromotionValidate(Long prmNo) {
        return promotionMapper.getPromotionValidate(prmNo);
    }


}
