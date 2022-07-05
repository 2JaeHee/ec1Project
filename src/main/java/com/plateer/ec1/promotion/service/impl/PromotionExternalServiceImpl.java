package com.plateer.ec1.promotion.service.impl;

import com.plateer.ec1.common.code.promotion.PRM0002Enum;
import com.plateer.ec1.common.model.promotion.CcCpnIssueModel;
import com.plateer.ec1.common.model.promotion.CcPrmBaseModel;
import com.plateer.ec1.promotion.mapper.CouponMapper;
import com.plateer.ec1.promotion.mapper.PromotionMapper;
import com.plateer.ec1.promotion.mapper.PromotionTrxMapper;
import com.plateer.ec1.promotion.service.PromotionExternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PromotionExternalServiceImpl implements PromotionExternalService {
    private final CouponMapper couponMapper;
    private final PromotionMapper promotionMapper;
    private final PromotionTrxMapper promotionTrxMapper;

    @Override
    @Transactional
    public void couponUse(CcCpnIssueModel ccCpnIssueModel) {
        promotionTrxMapper.modifyCouponUseInfo(ccCpnIssueModel);
    }

    @Override
    @Transactional
    public void couponUseCancel(CcCpnIssueModel paramModel) {

        dateValidate(paramModel);

        promotionTrxMapper.saveCouponRestore(paramModel);
    }

    private void dateValidate(CcCpnIssueModel paramModel) {
        CcPrmBaseModel prmBaseInfo = promotionMapper.getPrmBaseInfo(paramModel.getPrmNo());
        if (PRM0002Enum.PERIOD.getCode() == prmBaseInfo.getPrmPriodCcd()) {
            if(prmBaseInfo.periodValidate()){
                throw new ValidationException("프로모션 기간 종료 (기간)");
            }
        }
        if (PRM0002Enum.REFERENCE_DATE.getCode() == prmBaseInfo.getPrmPriodCcd()) {
            CcCpnIssueModel ccCpnIssueInfo = couponMapper.getCcCpnIssueInfo(paramModel);
            if (prmBaseInfo.referenceDateValidate(ccCpnIssueInfo.getSysModDtime())) {
                throw new ValidationException("프로모션 기간 종료 (기준일)");
            }
        }
    }

}
