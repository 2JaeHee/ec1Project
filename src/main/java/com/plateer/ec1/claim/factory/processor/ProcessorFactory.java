package com.plateer.ec1.claim.factory.processor;

import com.plateer.ec1.claim.enums.ClaimProcessorType;
import com.plateer.ec1.claim.vo.ClaimDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class ProcessorFactory {
    private final Map<ClaimProcessorType, ClaimProcessor> processorMap = new HashMap<>();

    public ProcessorFactory(List<ClaimProcessor> processors) {
        processors.forEach(c -> this.processorMap.put(c.getType(), c));

    }
    public ClaimProcessor getClaimProcessor(ClaimDto claimDto) {
        log.info("----------- Claim Processor : " + processorMap.get(claimDto.getClaimProcessorType()));
        return processorMap.get(claimDto.getClaimProcessorType());
    }
}
