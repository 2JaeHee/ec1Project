package com.plateer.ec1.payment.vo.franchisee;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Getter
public class FranchiseeReq {
    private String franchiseeNo;

    @NotNull
    private String clientIp;    //가맹점 요청 서버IP
    @NotNull
    private String mid;         //상점아이디
    @NotNull
    private String url;         //가맹점 URL

}
