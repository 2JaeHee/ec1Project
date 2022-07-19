package com.plateer.ec1.payment.vo.promotion.service;

import com.plateer.ec1.common.model.promotion.CcCpnIssueModel;

public interface PromotionExternalService {
    /**
     * 쿠폰 사용 (주문 프로세스에서 서비스 호출)
     * @param ccCpnIssueModel
     */
    void couponUse(CcCpnIssueModel ccCpnIssueModel);
    /**
     * 쿠폰 취소 (클레임 프로세스에서 서비스 호출)
     * @param ccCpnIssueModel
     */
    void couponUseCancel(CcCpnIssueModel ccCpnIssueModel);
}
