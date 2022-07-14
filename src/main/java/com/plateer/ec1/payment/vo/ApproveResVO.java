package com.plateer.ec1.payment.vo;

import com.plateer.ec1.payment.vo.inicis.InicisApproveRes;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApproveResVO {
    private String resultCd;
    private String resultMsg;

    //이니시스
    public static ApproveResVO of(InicisApproveRes res) {
        return ApproveResVO.builder()
                .resultCd(res.getResultCode())
                .resultMsg(res.getResultMsg())
                .build();
    }
}
