package com.plateer.ec1.promotion.service.impl;

import com.plateer.ec1.common.model.promotion.CcCpnBaseModel;
import com.plateer.ec1.common.model.promotion.CcCpnIssueModel;
import com.plateer.ec1.promotion.mapper.CouponMapper;
import com.plateer.ec1.promotion.mapper.PromotionTrxMapper;
import com.plateer.ec1.promotion.service.PromotionService;
import com.plateer.ec1.promotion.vo.req.RequestCouponIssueVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {

    private final CouponMapper couponMapper;
    private final PromotionTrxMapper promotionTrxMapper;

    @Transactional
    @Override
    public void couponDownload(RequestCouponIssueVo requestCouponIssueVo) {
        CcCpnBaseModel ccCpnBaseInfo = couponMapper.getCcCpnBaseInfo(requestCouponIssueVo.getPrmNo());
        //쿠폰 다운로드 가능 기간 체크 (쿠폰 다운로드 시 다운로드 가능기간을 이용해 다운로드 여부판단)
        if(ccCpnBaseInfo.periodValidate()) {
            downloadAvailableCountValidate(requestCouponIssueVo, ccCpnBaseInfo);

            CcCpnIssueModel ccCpnIssueModel = CcCpnIssueModel.builder()
                    .mbrNo(requestCouponIssueVo.getMbrNo())
                    .prmNo(requestCouponIssueVo.getPrmNo())
                    .build();

            promotionTrxMapper.saveCouponDownload(ccCpnIssueModel);
        }
    }

    private void downloadAvailableCountValidate(RequestCouponIssueVo requestCouponIssueVo, CcCpnBaseModel ccCpnBaseInfo) {

        CcCpnIssueModel ccCpnIssueModel = CcCpnIssueModel.builder().prmNo(requestCouponIssueVo.getPrmNo()).build();

        List<CcCpnIssueModel> ccCpnIssueList = couponMapper.getCcCpnIssueList(ccCpnIssueModel);

        long mbrCnt = ccCpnIssueList.stream().filter(o -> o.getMbrNo().equals(requestCouponIssueVo.getMbrNo())).count();
        if(!ccCpnBaseInfo.dwlPsbCntValid.test(ccCpnIssueList.size())){
            throw new ValidationException("쿠폰 다운로드 가능 수량 초과");
        }
        if(!ccCpnBaseInfo.psnDwlPsbCntValid.test((int)mbrCnt)){
            throw new ValidationException("개인별 쿠폰 다운로드 가능 수량 초과");
        }
    }

}
