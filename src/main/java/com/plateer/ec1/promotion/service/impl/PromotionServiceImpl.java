package com.plateer.ec1.promotion.service.impl;

import com.plateer.ec1.common.model.promotion.CcCpnBaseModel;
import com.plateer.ec1.common.model.promotion.CcCpnIssueModel;
import com.plateer.ec1.promotion.mapper.PromotionMapper;
import com.plateer.ec1.promotion.mapper.PromotionTrxMapper;
import com.plateer.ec1.promotion.service.PromotionService;
import com.plateer.ec1.promotion.vo.RequestPromotionVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {

    private final PromotionMapper promotionMapper;
    private final PromotionTrxMapper promotionTrxMapper;

    @Override
    public List<CcCpnBaseModel> getAvailableCouponList(RequestPromotionVo requestPromotionVo) {
        return promotionMapper.getAvailableCouponList(requestPromotionVo);
    }

    @Override
    public void saveCouponDownload(CcCpnIssueModel ccCpnIssueModel) {
        promotionTrxMapper.saveCouponDownload(ccCpnIssueModel);
    }
}
