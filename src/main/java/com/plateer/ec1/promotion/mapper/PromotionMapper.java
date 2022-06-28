package com.plateer.ec1.promotion.mapper;

import com.plateer.ec1.common.model.promotion.CcCpnIssueModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PromotionMapper {
    /**
     * 다운로드 가능 쿠폰 검증
     * @param ccCpnIssueModel
     * @return boolean
     */
    boolean getAvailableCouponValidate(CcCpnIssueModel ccCpnIssueModel);
    /**
     * 사용취소를 위해 프로모션 기간 검증
     * @param prmNo
     * @return boolean
     */
    boolean getPromotionValidate(Long prmNo);


}
