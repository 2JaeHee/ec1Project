package com.plateer.ec1.claim.vo;

import com.plateer.ec1.claim.enums.ClaimType;
import com.plateer.ec1.claim.enums.ClaimProcessorType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClaimDto {
    private ClaimType type;
    private ClaimProcessorType claimType;
}
