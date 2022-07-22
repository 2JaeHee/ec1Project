package com.plateer.ec1.promotion.service;

import com.plateer.ec1.promotion.vo.req.RequestCouponIssueVo;

public interface PromotionService {
    /**
     * 쿠폰 다운로드
     * @param requestCouponIssueVo
     */
    void couponDownload(RequestCouponIssueVo requestCouponIssueVo);
}
