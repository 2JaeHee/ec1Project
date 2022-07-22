package com.plateer.ec1.payment.vo.promotion.service.impl;

import com.plateer.ec1.common.code.promotion.PRM0011Enum;
import com.plateer.ec1.common.model.promotion.CcMbrPntModel;
import com.plateer.ec1.promotion.mapper.PointTrxMapper;
import com.plateer.ec1.promotion.service.PointService;
import com.plateer.ec1.promotion.vo.req.PointReqVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PointServiceImplTest {
    @Autowired
    PointService pointService;

    @Autowired
    PointTrxMapper pointTrxMapper;

    @Test
    void mainSavePointInfo_ACCUMULATE() {
        String mbrNo = "test02";
        double amt = 2000;
        PointReqVo pointReqVo = PointReqVo.builder().mbrNo(mbrNo).pntBlc(amt).build();
        pointService.savePointInfo(pointReqVo, PRM0011Enum.ACCUMULATE);
    } @Test
    void mainSavePointInfo_USE() {
        String mbrNo = "test02";
        double amt = 4000;
        String ordNo = "O22207200002";
        String payNo = "2";
        PointReqVo pointReqVo = PointReqVo.builder()
                .mbrNo(mbrNo)
                .pntBlc(amt)
                .ordNo(ordNo)
                .payNo(payNo)
                .build();
        pointService.savePointInfo(pointReqVo, PRM0011Enum.USE);
    } @Test
    void mainSavePointInfo_CANCEL() {
        String mbrNo = "test02";
        double amt = 1000;
        String ordNo = "O22207200001";
        String payNo = "1";
        PointReqVo pointReqVo = PointReqVo.builder()
                .mbrNo(mbrNo)
                .pntBlc(amt)
                .ordNo(ordNo)
                .payNo(payNo)
                .build();
        pointService.savePointInfo(pointReqVo, PRM0011Enum.CANCEL);
    }

    @Test
    void getPointInfo() {
        String mbrNo = "test01";
        CcMbrPntModel pointInfo = pointService.getPointInfo(mbrNo);
    }

    @Test
    void savePointInfo() throws Exception {
        String mbrNo = "test01";
        double amt = 10000;
        PointReqVo pointReqVo = PointReqVo.builder().mbrNo(mbrNo).pntBlc(amt).build();
        pointService.savePointInfo(pointReqVo, PRM0011Enum.ACCUMULATE);
    }

    @Test
    void usePoint() {
        String mbrNo = "test01";
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
        String mbrNo = "test01";
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