package com.plateer.ec1.payment.vo.promotion.service.impl;

import com.plateer.ec1.common.code.promotion.PRM0011Enum;
import com.plateer.ec1.common.model.promotion.CcMbrPntModel;
import com.plateer.ec1.payment.vo.promotion.mapper.PointMapper;
import com.plateer.ec1.payment.vo.promotion.mapper.PointTrxMapper;
import com.plateer.ec1.payment.vo.promotion.service.PointService;
import com.plateer.ec1.payment.vo.promotion.vo.req.PointReqVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PointServiceImpl implements PointService {
    private final PointMapper pointMapper;
    private final PointTrxMapper pointTrxMapper;

    @Override
    public CcMbrPntModel getPointInfo(String mbrNo) {
        return pointMapper.getPointInfo(mbrNo);
    }

    @Override
    @Transactional
    public void savePointInfo(PointReqVo pointReqVo, PRM0011Enum accumulate) throws IllegalArgumentException {
        CcMbrPntModel ccMbrPntModel = CcMbrPntModel.builder().build();

        switch (accumulate) {
            case ACCUMULATE:
                ccMbrPntModel = CcMbrPntModel.accumulate(pointReqVo);
                break;
            case USE:
                ccMbrPntModel = CcMbrPntModel.use(pointReqVo, getPointInfo(pointReqVo.getMbrNo()));
                break;
            case CANCEL:
                ccMbrPntModel = CcMbrPntModel.cancel(pointReqVo, getPointInfo(pointReqVo.getMbrNo()));
                break;
            default:
                throw new IllegalArgumentException();
        }
        pointTrxMapper.savePointInfo(ccMbrPntModel);
    }
}
