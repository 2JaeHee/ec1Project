package com.plateer.ec1.promotion.mapper;

import com.plateer.ec1.common.model.promotion.CcCpnBaseModel;
import com.plateer.ec1.promotion.vo.RequestPromotionVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PromotionMapper {
    /**
     * 다운로드 가능한 쿠폰 목록 조회
     */
    List<CcCpnBaseModel> getAvailableCouponList(RequestPromotionVo requestPromotionVo);
}
