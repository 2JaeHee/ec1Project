package com.plateer.ec1.claim.service.impl;
import com.plateer.ec1.claim.factory.processor.ClaimProcessor;
import com.plateer.ec1.claim.factory.processor.ProcessorFactory;
import com.plateer.ec1.claim.service.ClaimService;
import com.plateer.ec1.claim.vo.ClaimDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClaimServiceImpl implements ClaimService {
    private final ProcessorFactory processorFactory;

    public void getClaim(ClaimDto claimDto){
        ClaimProcessor claimProcessor = processorFactory.getClaimProcessor(claimDto);
        claimProcessor.doProcess(claimDto);
    }
}
