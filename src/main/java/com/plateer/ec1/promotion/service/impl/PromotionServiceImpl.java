package com.plateer.ec1.promotion.service.impl;

import com.plateer.ec1.common.model.promotion.CcCpnIssueModel;
import com.plateer.ec1.promotion.mapper.PromotionMapper;
import com.plateer.ec1.promotion.mapper.PromotionTrxMapper;
import com.plateer.ec1.promotion.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {

    private final PromotionMapper promotionMapper;
    private final PromotionTrxMapper promotionTrxMapper;

    @Override
    public void couponDownload(CcCpnIssueModel ccCpnIssueModel) {
        if(availableCouponValidate(ccCpnIssueModel)){
            promotionTrxMapper.saveCouponDownload(ccCpnIssueModel);
        }
    }

    private boolean availableCouponValidate(CcCpnIssueModel ccCpnIssueModel) {
        return promotionMapper.getAvailableCouponValidate(ccCpnIssueModel);
    }
}
