package com.plateer.ec1.claim.validator;

import com.plateer.ec1.claim.vo.ClaimDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ClaimValidator {

    public void isValidStatus(ClaimDto claimDto) {
        log.info("[ClaimValidator.isValidStatus]");
    }

    public void isValidAmount(ClaimDto claimDto) {
        log.info("[ClaimValidator.isValidAmount]");
    }

    public void verifyAmount(ClaimDto claimDto) {
        log.info("[ClaimValidator.verifyAmount]");
    }
}
