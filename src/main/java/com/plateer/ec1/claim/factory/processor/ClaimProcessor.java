package com.plateer.ec1.claim.factory.processor;

import com.plateer.ec1.claim.enums.ClaimProcessorType;
import com.plateer.ec1.claim.vo.ClaimDto;

public interface ClaimProcessor {

    void doValidationProcess(ClaimDto claimDto);
    void verifyAmount(ClaimDto claimDto);
    void doProcess(ClaimDto claimDto);

    ClaimProcessorType getType();

}
