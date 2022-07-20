package com.plateer.ec1.payment.vo.promotion.service.impl;

import com.plateer.ec1.common.code.promotion.PRM0011Enum;
import com.plateer.ec1.common.model.promotion.CcMbrPntModel;
import com.plateer.ec1.payment.vo.promotion.mapper.PointTrxMapper;
import com.plateer.ec1.payment.vo.promotion.service.PointService;
import com.plateer.ec1.payment.vo.promotion.vo.req.PointReqVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PointServiceImplTest {
    @Autowired
    PointService pointService;

    @Autowired
    PointTrxMapper pointTrxMapper;

    private final String mbrNo = "test01";

    @Test
    void getPointInfo() {
        CcMbrPntModel pointInfo = pointService.getPointInfo(mbrNo);
    }


    @Test
    void savePointInfo() throws Exception {
        double amt = 10000;
        PointReqVo pointReqVo = PointReqVo.builder().mbrNo(mbrNo).pntBlc(amt).build();
        pointService.savePointInfo(pointReqVo, PRM0011Enum.ACCUMULATE);
    }

    @Test
    void usePoint() {
        double amt = 1000;
        String ordNo = "O22207200001";
        String payNo = "1";
        CcMbrPntModel reqPointInfo = pointService.getPointInfo(mbrNo);
        CcMbrPntModel ccMbrPntModel = CcMbrPntModel.builder()
                .mbrNo(mbrNo)
                .svUseCcd(PRM0011Enum.USE.getCode())
                .svUseAmt(amt)
                .pntBlc(reqPointInfo.getPntBlc() - amt)
                .ordNo(ordNo)
                .payNo(payNo)
                .build();
        pointTrxMapper.savePointInfo(ccMbrPntModel);

    }

    @Test
    void cancelPoint() {
        double amt = 1000;
        String ordNo = "O22207200001";
        String payNo = "2";
        CcMbrPntModel pointInfo = pointService.getPointInfo(mbrNo);
        CcMbrPntModel ccMbrPntModel = CcMbrPntModel.builder()
                .mbrNo(mbrNo)
                .svUseCcd(PRM0011Enum.ACCUMULATE.getCode())
                .svUseAmt(amt)
                .pntBlc(pointInfo.getPntBlc() + amt)
                .ordNo(ordNo)
                .payNo(payNo)
                .build();
        pointTrxMapper.savePointInfo(ccMbrPntModel);
    }
}