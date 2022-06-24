package com.plateer.ec1.claim.factory.impl;

import com.plateer.ec1.claim.creator.ClaimDataCreator;
import com.plateer.ec1.claim.enums.ClaimProcessorType;
import com.plateer.ec1.claim.enums.ClaimType;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CreatorFactory {
    Map<ClaimType, ClaimDataCreator> creatorMap = new LinkedHashMap<>();
    List<ClaimDataCreator> creators;

    public CreatorFactory(List<ClaimDataCreator> creators) {
        this.creators = creators;
    }

    @PostConstruct
    void init() {
        creators.stream()
                .forEach(c -> creatorMap.put(c.getType(), c));
    }

    public ClaimDataCreator getCreator(ClaimProcessorType type){
        if(!creatorMap.containsKey(type)) { throw new IllegalArgumentException(""); }
        return creatorMap.get(type);
    }
}
