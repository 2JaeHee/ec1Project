package com.plateer.ec1.payment.vo.member;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Getter
public class MemberReq {
    private String mbrNo;
    @NotEmpty
    private String mbrNm;
    @NotEmpty
    @Email
    private String mbrEmail;
    @NotEmpty
    private String mbrPhoneNo;
}
