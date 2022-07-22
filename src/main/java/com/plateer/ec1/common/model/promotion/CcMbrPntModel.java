package com.plateer.ec1.common.model.promotion;

import com.plateer.ec1.common.code.promotion.PRM0011Enum;
import com.plateer.ec1.common.code.promotion.PromotionConstants;
import com.plateer.ec1.promotion.vo.req.PointReqVo;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

@Builder
@Getter
public class CcMbrPntModel {
    private Long pntHstSeq;
    private String mbrNo;
    private String svUseCcd;    //PRM0011 적립사용구분코드 (적립, 사용)
    private Double svUseAmt;
    private Double pntBlc;
    private String ordNo;
    private String payNo;
    private LocalDateTime sysRegDtime;
    @Builder.Default
    private String sysRegrId = PromotionConstants.ADMIN;
    private LocalDateTime sysModDtime;
    @Builder.Default
    private String sysModrId = PromotionConstants.ADMIN;

    //적립
    public static CcMbrPntModel accumulate(PointReqVo pointReqVo, CcMbrPntModel getPointInfo) {
        return CcMbrPntModel.builder()
                .mbrNo(pointReqVo.getMbrNo())
                .svUseCcd(PRM0011Enum.ACCUMULATE.getCode())
                .svUseAmt(pointReqVo.getPntBlc())
                .pntBlc(ObjectUtils.isEmpty(getPointInfo)? pointReqVo.getPntBlc() : getPointInfo.pntBlc + pointReqVo.getPntBlc())
                .build();
    }
    //사용
    public static CcMbrPntModel use(PointReqVo pointReqVo, CcMbrPntModel getPointInfo) {
        return CcMbrPntModel.builder()
                .mbrNo(pointReqVo.getMbrNo())
                .svUseCcd(PRM0011Enum.USE.getCode())
                .svUseAmt(pointReqVo.getPntBlc())
                .pntBlc(getPointInfo.getPntBlc() - pointReqVo.getPntBlc())
                .ordNo(pointReqVo.getOrdNo())
                .payNo(pointReqVo.getPayNo())
                .build();
    }
    //취소
    public static CcMbrPntModel cancel(PointReqVo pointReqVo, CcMbrPntModel getPointInfo) {
        return CcMbrPntModel.builder()
                .mbrNo(pointReqVo.getMbrNo())
                .svUseCcd(PRM0011Enum.ACCUMULATE.getCode())
                .svUseAmt(pointReqVo.getPntBlc())
                .pntBlc(getPointInfo.getPntBlc() + pointReqVo.getPntBlc())
                .ordNo(pointReqVo.getOrdNo())
                .payNo(pointReqVo.getPayNo())
                .build();
    }
}
