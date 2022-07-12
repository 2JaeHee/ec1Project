package com.plateer.ec1.payment.vo.order;

import com.plateer.ec1.common.validator.ValidEnum;
import com.plateer.ec1.payment.enums.BankCode;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Getter
public class OrderReq {
    @NotEmpty
    private String ordNo;
    @NotEmpty
    private String goodsNm;
    @NotNull
    private Long prc;
    @ValidEnum(enumClass = BankCode.class)
    private BankCode bankCode;        //은행코드 enum 필요
}
