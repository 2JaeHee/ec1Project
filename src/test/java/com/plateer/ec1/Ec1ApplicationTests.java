package com.plateer.ec1;

import com.plateer.ec1.claim.mapper.ClaimMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Ec1ApplicationTests {

    @Autowired
    ClaimMapper claimMapper;

    @Test
    void test() {
        claimMapper.getTest();
    }
}
