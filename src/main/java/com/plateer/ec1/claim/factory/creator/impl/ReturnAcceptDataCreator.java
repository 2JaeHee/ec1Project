package com.plateer.ec1.claim.factory.creator.impl;
import com.plateer.ec1.claim.enums.ClaimType;
import com.plateer.ec1.claim.factory.creator.ClaimDataCreator;
import com.plateer.ec1.claim.vo.ClaimDto;
import com.plateer.ec1.claim.vo.ClaimModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReturnAcceptDataCreator implements ClaimDataCreator {
    @Override
    public ClaimModel getInsertClaimData(ClaimDto claimDto) {
        log.info("[ReturnAcceptDataCreator.getUpdateClaimData]");
        return new ClaimModel();
    }

    @Override
    public ClaimModel getUpdateClaimData(ClaimDto claimDto) {
        log.info("[ReturnAcceptDataCreator.getUpdateClaimData]");
        return new ClaimModel();
    }

    @Override
    public ClaimType getType() {
        return ClaimType.RA;
    }
}
