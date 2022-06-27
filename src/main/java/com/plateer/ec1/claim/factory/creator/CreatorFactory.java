package com.plateer.ec1.claim.factory.creator;

import com.plateer.ec1.claim.enums.ClaimProcessorType;
import com.plateer.ec1.claim.enums.ClaimType;
import com.plateer.ec1.claim.vo.ClaimDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class CreatorFactory {
    private static final Map<ClaimType, ClaimDataCreator> creatorMap = new HashMap<>();

    public CreatorFactory(List<ClaimDataCreator> creators) {
        creators.stream().forEach(c -> creatorMap.put(c.getType(), c));
    }

    public static ClaimDataCreator getCreator(ClaimDto claimDto){
        log.info("----------- Claim Creator : " + creatorMap.get(claimDto.getClaimType()));
        return creatorMap.get(claimDto.getClaimType());
    }
}
