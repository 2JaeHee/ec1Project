package com.plateer.ec1.promotion.mapper;

import com.plateer.ec1.common.model.promotion.CcCpnBaseModel;
import com.plateer.ec1.common.model.promotion.CcCpnIssueModel;
import com.plateer.ec1.common.model.promotion.CcPrmBaseModel;
import com.plateer.ec1.promotion.vo.Promotion;
import com.plateer.ec1.promotion.vo.req.PromotionApplyTargetVo;
import com.plateer.ec1.promotion.vo.req.RequestPromotionVo;
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
     * 상품 별 프로모션 적용 대상 조회
     * @param vo
     * @return List<AvailablePromotionResVo>
     */
    List<Promotion> getPrmAplyTgtList(PromotionApplyTargetVo vo);

}
