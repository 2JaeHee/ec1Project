package com.plateer.ec1.promotion.mapper;

import com.plateer.ec1.common.model.promotion.CcCpnBaseModel;
import com.plateer.ec1.common.model.promotion.CcCpnIssueModel;
import com.plateer.ec1.common.model.promotion.CcPrmBaseModel;
import com.plateer.ec1.promotion.vo.CcCpnIssueReqVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PromotionMapper {
    /**
     * 프로모션 기본 정보 조회
     * @param prmNo
     * @return CcPrmBaseModel
     */
    CcPrmBaseModel getPrmBaseInfo(Long prmNo);

    /**
     * 쿠폰 기본 정보 조회
     * @param prmNo
     * @return CcCpnBaseModel
     */
    CcCpnBaseModel getCcCpnBaseInfo(Long prmNo);

    /**
     * 쿠폰 발급회원 정보 조회
     * @param ccCpnIssueReqVo
     * @return List<CcCpnIssueModel>
     */
    List<CcCpnIssueModel> getCcCpnIssueList(CcCpnIssueReqVo ccCpnIssueReqVo);
    /**
     * 다운로드 가능 쿠폰 검증
     * @param ccCpnIssueModel
     * @return boolean
     */
    boolean getAvailableCouponValidate(CcCpnIssueModel ccCpnIssueModel);


}
