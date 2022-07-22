package com.plateer.ec1.payment.vo;

import com.plateer.ec1.payment.vo.inicis.InicisApproveRes;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CancelRes {
    private String resultCd;
    private String resultMsg;

    public static CancelRes of(String resultCd, String resultMsg) {
        return CancelRes.builder().resultCd(resultCd).resultMsg(resultMsg).build();
    }
}
