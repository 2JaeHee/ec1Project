package com.plateer.ec1.promotion.service.impl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.plateer.ec1.common.model.promotion.CcCpnBaseModel;
import com.plateer.ec1.common.model.promotion.CcCpnIssueModel;
import com.plateer.ec1.promotion.mapper.PromotionMapper;
import com.plateer.ec1.promotion.mapper.PromotionTrxMapper;
import com.plateer.ec1.promotion.service.PromotionService;
import com.plateer.ec1.promotion.vo.CcCpnIssueReqVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {

    private final PromotionMapper promotionMapper;
    private final PromotionTrxMapper promotionTrxMapper;

    @Transactional
    @Override
    public void couponDownload(CcCpnIssueReqVo ccCpnIssueReqVo) {
        CcCpnBaseModel ccCpnBaseInfo = promotionMapper.getCcCpnBaseInfo(ccCpnIssueReqVo.getPrmNo());
        //쿠폰 다운로드 가능 기간 체크
        if(ccCpnBaseInfo.periodValidate()) {
            downloadAvailableCountValidate(ccCpnIssueReqVo, ccCpnBaseInfo);

            CcCpnIssueModel ccCpnIssueModel = CcCpnIssueModel.builder().build();
            BeanUtils.copyProperties(ccCpnIssueReqVo, ccCpnIssueModel);

            promotionTrxMapper.saveCouponDownload(ccCpnIssueModel);
        }
    }

    private void downloadAvailableCountValidate(CcCpnIssueReqVo ccCpnIssueReqVo, CcCpnBaseModel ccCpnBaseInfo) {

        CcCpnIssueModel ccCpnIssueModel = CcCpnIssueModel.builder().prmNo(ccCpnIssueReqVo.getPrmNo()).build();

        List<CcCpnIssueModel> ccCpnIssueList = promotionMapper.getCcCpnIssueList(ccCpnIssueModel);

        long mbrCnt = ccCpnIssueList.stream().filter(o -> o.getMbrNo().equals(ccCpnIssueReqVo.getMbrNo())).count();
        if(!ccCpnBaseInfo.dwlPsbCntValid.test(ccCpnIssueList.size())){
            throw new ValidationException("쿠폰 다운로드 가능 수량 초과");
        }
        if(!ccCpnBaseInfo.psnDwlPsbCntValid.test((int)mbrCnt)){
            throw new ValidationException("개인별 쿠폰 다운로드 가능 수량 초과");
        }
    }

}
