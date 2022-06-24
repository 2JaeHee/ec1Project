package com.plateer.ec1.claim.factory;

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
    private final List<ClaimProcessor> processors;

    public ProcessorFactory(List<ClaimProcessor> processors) {
        this.processors = processors;
        this.processors.forEach(c -> this.processorMap.put(c.getType(), c));
        System.out.println("processors = " + processors);
        System.out.println("processorMap = " + processorMap);

    }
    public ClaimProcessor getClaimProcessor(ClaimDto claimDto) {
        log.info("----------- Claim : " + processorMap.get(claimDto.getClaimType()));
        return processorMap.get(claimDto.getClaimType());
    }
}
