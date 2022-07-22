package com.plateer.ec1.promotion.service.impl;

import com.plateer.ec1.common.code.promotion.PRM0011Enum;
import com.plateer.ec1.common.model.promotion.CcMbrPntModel;
import com.plateer.ec1.promotion.mapper.PointMapper;
import com.plateer.ec1.promotion.mapper.PointTrxMapper;
import com.plateer.ec1.promotion.service.PointService;
import com.plateer.ec1.promotion.vo.req.PointReqVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
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
    public void savePointInfo(PointReqVo pointReqVo, PRM0011Enum accumulate)  {
        CcMbrPntModel ccMbrPntModel = CcMbrPntModel.builder().build();
        //포인트 조회
        CcMbrPntModel getPointInfo = getPointInfo(pointReqVo.getMbrNo());

        switch (accumulate) {
            case ACCUMULATE:
                ccMbrPntModel = CcMbrPntModel.accumulate(pointReqVo, getPointInfo);
                break;
            case USE:
                ccMbrPntModel = CcMbrPntModel.use(pointReqVo, getPointInfo);
                break;
            case CANCEL:
                ccMbrPntModel = CcMbrPntModel.cancel(pointReqVo, getPointInfo);
                break;
            default:
                throw new IllegalArgumentException();
        }

        pointTrxMapper.savePointInfo(ccMbrPntModel);
    }
}
