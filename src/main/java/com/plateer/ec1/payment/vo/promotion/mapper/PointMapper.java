package com.plateer.ec1.payment.vo.promotion.mapper;

import com.plateer.ec1.common.model.promotion.CcMbrPntModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PointMapper {
    /**
     * 회원 별 포인트 조회
     * @param mbrNo
     * @return
     */
    CcMbrPntModel getPointInfo(String mbrNo);
}
