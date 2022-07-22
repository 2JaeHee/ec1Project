package com.plateer.ec1.promotion.service;

import com.plateer.ec1.common.code.promotion.PRM0011Enum;
import com.plateer.ec1.common.model.promotion.CcMbrPntModel;
import com.plateer.ec1.promotion.vo.req.PointReqVo;

public interface PointService {
    /**
     * 회원 별 포인트조회
     * @param mbrNo
     * @return
     */
    CcMbrPntModel getPointInfo(String mbrNo);

    /**
     * 회원별 포인트 적립/사용/취소
     * @param pointReqVo
     * @param accumulate
     */
    void savePointInfo(PointReqVo pointReqVo, PRM0011Enum accumulate);

}
