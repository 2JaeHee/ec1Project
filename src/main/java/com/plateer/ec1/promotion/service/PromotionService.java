package com.plateer.ec1.promotion.service;

import com.plateer.ec1.common.model.promotion.CcCpnBaseModel;
import com.plateer.ec1.common.model.promotion.CcCpnIssueModel;
import com.plateer.ec1.promotion.vo.RequestPromotionVo;

import java.util.List;

public interface PromotionService {

    /**
     * 다운로드 가능한 쿠폰 목록 조회
     */
    /**
     * 다운로드 가능한 쿠폰 목록 조회
     * @param requestPromotionVo
     * @return
     */
    List<CcCpnBaseModel> getAvailableCouponList(RequestPromotionVo requestPromotionVo);

    /**
     * 쿠폰 다운로드
     * @param ccCpnIssueModel
     */
    void saveCouponDownload(CcCpnIssueModel ccCpnIssueModel);
}
