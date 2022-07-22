package com.plateer.ec1.payment.vo;

import com.plateer.ec1.payment.vo.inicis.InicisApproveRes;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class ApproveRes {
    private String resultCd;
    private String resultMsg;

    //이니시스
    public static ApproveRes of(InicisApproveRes res) {
        return ApproveRes.builder()
                .resultCd(res.getResultCode())
                .resultMsg(res.getResultMsg())
                .build();
    }
}
