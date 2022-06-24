package com.plateer.ec1.payment.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InicisApproveRes {
    private String resultCode;
    private String resultMsg;
    private String tid;

    public static ApproveResVO build(InicisApproveRes inicisApproveRes){
        return ApproveResVO.builder()
                .resultCd(inicisApproveRes.resultCode)
                .build();
    }
}
