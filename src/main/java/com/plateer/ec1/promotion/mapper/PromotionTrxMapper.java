package com.plateer.ec1.promotion.mapper;

import com.plateer.ec1.common.model.promotion.CcCpnIssueModel;
import com.plateer.ec1.promotion.vo.CcCpnIssueReqVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PromotionTrxMapper {
    /**
     * 쿠폰 다운로드
     * @param ccCpnIssueReqVo
     */
    void saveCouponDownload(CcCpnIssueReqVo ccCpnIssueReqVo);

    /**
     * 쿠폰 사용 처리
     * @param ccCpnIssueModel
     */
    void modifyCouponUseInfo(CcCpnIssueModel ccCpnIssueModel);

    /**
     * 취소 시 쿠폰 복원 처리
     * @param ccCpnIssueModel
     */
    void saveCouponRestore(CcCpnIssueModel ccCpnIssueModel);
}
