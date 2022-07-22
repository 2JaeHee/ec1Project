package com.plateer.ec1.promotion.controller;

import com.plateer.ec1.common.code.promotion.PRM0011Enum;
import com.plateer.ec1.common.model.promotion.CcMbrPntModel;
import com.plateer.ec1.promotion.service.PointService;
import com.plateer.ec1.promotion.vo.req.PointReqVo;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/point")
public class PointController {

    private final PointService pointService;

    /**
     * 회원 별 포인트 조회
     * @param mbrNo
     * @return CcMbrPntModel
     */
    @GetMapping( "/getMemberPointInfo/{mbrNo}")
    public CcMbrPntModel getPointInfo(@PathVariable String mbrNo) {
        return pointService.getPointInfo(mbrNo);
    }

    /**
     * 회원별 포인트 적립
     * @param pointReqVo
     * @return CcMbrPntModel
     */
    @PutMapping( "/saveMemberPoint")
    public CcMbrPntModel savePoint(@RequestBody PointReqVo pointReqVo) {
        pointService.savePointInfo(pointReqVo, PRM0011Enum.ACCUMULATE);
        return pointService.getPointInfo(pointReqVo.getMbrNo());
    }

    /**
     * 포인트 사용
     * @param pointReqVo
     * @return CcMbrPntModel
     */
    @PutMapping("/usePoint")
    public CcMbrPntModel usePoint(@RequestBody PointReqVo pointReqVo) {
        pointService.savePointInfo(pointReqVo, PRM0011Enum.USE);
        return pointService.getPointInfo(pointReqVo.getMbrNo());
    }

    /**
     * 회원 포인트 사용 취소
     * @param pointReqVo
     * @return CcMbrPntModel
     */
    @PutMapping("/cancelPoint")
    public CcMbrPntModel cancelPoint(@RequestBody PointReqVo pointReqVo) {
        pointService.savePointInfo(pointReqVo, PRM0011Enum.CANCEL);
        return pointService.getPointInfo(pointReqVo.getMbrNo());
    }
}
