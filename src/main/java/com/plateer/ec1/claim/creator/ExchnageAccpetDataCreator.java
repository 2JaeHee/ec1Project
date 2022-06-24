package com.plateer.ec1.claim.creator;

import com.plateer.ec1.claim.enums.ClaimType;
import com.plateer.ec1.claim.vo.ClaimDto;
import com.plateer.ec1.claim.vo.ClaimModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ExchnageAccpetDataCreator implements ClaimDataCreator {
    @Override
    public ClaimModel getInsertClaimData(ClaimDto claimDto) {
        log.info("[ExchnageAccpetDataCreator.getInsertClaimData]");
        return new ClaimModel();
    }

    @Override
    public ClaimModel getUpdateClaimData(ClaimDto claimDto) {
        log.info("[ExchnageAccpetDataCreator.getUpdateClaimData]");
        return new ClaimModel();
    }

    @Override
    public ClaimType getType() {
        return ClaimType.EA;
    }
}
