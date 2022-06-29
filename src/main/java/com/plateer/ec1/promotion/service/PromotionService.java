package com.plateer.ec1.promotion.service;

import com.plateer.ec1.promotion.vo.CcCpnIssueReqVo;

public interface PromotionService {
    /**
     * 쿠폰 다운로드
     * @param ccCpnIssueReqVo
     */
    void couponDownload(CcCpnIssueReqVo ccCpnIssueReqVo);
}
