package com.plateer.ec1.promotion.mapper;

import com.plateer.ec1.common.model.promotion.CcCpnBaseModel;
import com.plateer.ec1.common.model.promotion.CcCpnIssueModel;
import com.plateer.ec1.common.model.promotion.CcPrmBaseModel;
import com.plateer.ec1.promotion.vo.AvailablePromotionResVo;
import com.plateer.ec1.promotion.vo.RequestPromotionVo;
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
     * 프로모션 별 쿠폰 발급회원 정보 목록 조회
     * @param ccCpnIssueModel
     * @return List<CcCpnIssueModel>
     */
    List<CcCpnIssueModel> getCcCpnIssueList(CcCpnIssueModel ccCpnIssueModel);

    /**
     * 쿠폰 발급회원 정보 조회
     * @param ccCpnIssueModel
     * @return CcCpnIssueModel
     */
    CcCpnIssueModel getCcCpnIssueInfo(CcCpnIssueModel ccCpnIssueModel);

    /**
     * 상품 별 프로모션 적용 대상 조회
     * @param requestPromotionVo
     * @return List<AvailablePromotionResVo>
     */
    List<AvailablePromotionResVo> getPrmAplyTgtList(RequestPromotionVo requestPromotionVo);
}
