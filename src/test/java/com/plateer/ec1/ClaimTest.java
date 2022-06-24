package com.plateer.ec1;

import com.plateer.ec1.claim.enums.ClaimType;
import com.plateer.ec1.claim.enums.ClaimProcessorType;
import com.plateer.ec1.claim.service.ClaimService;
import com.plateer.ec1.claim.vo.ClaimDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClaimTest {

    @Autowired
    ClaimService claimService;

    @Test
    @DisplayName("일반주문취소완료")
    void generalCancel(){
        ClaimDto claimDto = new ClaimDto();
        claimDto.setType(ClaimType.GCC);
        claimDto.setClaimType(ClaimProcessorType.COMPLETE);
        claimService.getClaim(claimDto);
    }
//
//    @Test
//    @DisplayName("모바일쿠폰 취소접수")
//    void mobileCancelAccept(){
//        ClaimProcessor claimProcessor = ClaimType.MCA.getClaimProcessor().get();
//        claimProcessor.doProcess(new ClaimDto());
//    }
//
//    @Test
//    @DisplayName("모바일쿠폰 취소완료")
//    void mobileCancelComplate(){
//        ClaimProcessor claimProcessor = ClaimType.MCC.getClaimProcessor().get();
//        claimProcessor.doProcess(new ClaimDto());
//    }
//
//    @Test
//    @DisplayName("반품접수")
//    void returnAccept(){
//        ClaimProcessor claimProcessor = ClaimType.RA.getClaimProcessor().get();
//        claimProcessor.doProcess(new ClaimDto());
//    }
//
//    @Test
//    @DisplayName("반품완료")
//    void returnComplate(){
//        ClaimProcessor claimProcessor = ClaimType.RC.getClaimProcessor().get();
//        claimProcessor.doProcess(new ClaimDto());
//    }
//
//    @Test
//    @DisplayName("반품철회")
//    void returnWithdrawal(){
//        ClaimProcessor claimProcessor = ClaimType.RW.getClaimProcessor().get();
//        claimProcessor.doProcess(new ClaimDto());
//    }
//
//    @Test
//    @DisplayName("교환접수")
//    void exchangeAccept(){
//        ClaimProcessor claimProcessor = ClaimType.EA.getClaimProcessor().get();
//        claimProcessor.doProcess(new ClaimDto());
//    }
//
//    @Test
//    @DisplayName("교환철회")
//    void exchangeWithdrawal(){
//        ClaimProcessor claimProcessor = ClaimType.EW.getClaimProcessor().get();
//        claimProcessor.doProcess(new ClaimDto());
//    }
//
}
