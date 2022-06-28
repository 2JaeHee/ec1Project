package com.plateer.ec1.promotion.service;

import com.plateer.ec1.common.model.promotion.CcCpnIssueModel;

public interface PromotionService {
    /**
     * 쿠폰 다운로드
     * @param ccCpnIssueModel
     */
    void couponDownload(CcCpnIssueModel ccCpnIssueModel);
}
