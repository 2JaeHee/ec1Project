package com.plateer.ec1.claim.factory.processor.impl;

import com.plateer.ec1.claim.enums.ClaimProcessorType;
import com.plateer.ec1.claim.factory.creator.ClaimDataCreator;
import com.plateer.ec1.claim.factory.creator.CreatorFactory;
import com.plateer.ec1.claim.factory.processor.ClaimProcessor;
import com.plateer.ec1.claim.logHelper.MonitoringLogHelper;
import com.plateer.ec1.claim.validator.ClaimValidator;
import com.plateer.ec1.claim.vo.ClaimDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

//완료
@Slf4j
@Service
@RequiredArgsConstructor
public class CompleteProcessor implements ClaimProcessor {
    private final ClaimValidator claimValidator;
    private final MonitoringLogHelper monitoringLogHelper;

    @Override
    public ClaimProcessorType getType() {
        return ClaimProcessorType.COMPLETE;
    }
    @Override
    public void doValidationProcess(ClaimDto claimDto) {
        claimValidator.isValidStatus(claimDto);
        log.info("[CompleteProcessor.doValidationProcess] doValidationProcess");
    }

    @Override
    public void verifyAmount(ClaimDto claimDto) {
        claimValidator.verifyAmount(claimDto);
        log.info("[CompleteProcessor.verifyAmount] verifyAmount");
    }

    @Override
    public void doProcess(ClaimDto claimDto) {
        ClaimDataCreator claimDataCreator = CreatorFactory.getCreator(claimDto);

        log.info("----------- [CompleteProcessor.doProcess] 클레임 완료 프로세서");
        log.info("----------- [CompleteProcessor.doProcess] 모니터링 insert");
        Long logNo = monitoringLogHelper.insertMonitoringLog("1");

        log.info("----------- [CompleteProcessor.doProcess] claim validation 체크");
        doValidationProcess(claimDto);

        log.info("----------- [CompleteProcessor.doProcess] 클레임 data insert");
        claimDataCreator.getInsertClaimData(claimDto);
        claimDataCreator.getUpdateClaimData(claimDto);

        log.info("----------- [CompleteProcessor.doProcess] 금액검증");
        verifyAmount(claimDto);

        log.info("----------- [CompleteProcessor.doProcess] 모니터링 update");
        monitoringLogHelper.updateMonitortingLog(logNo, "complete");
        
        //1. 로그 , 2. vaildation, 3. insert data 생성 , 4. 결제/외부 인터페이스 호출, 5.금액검증
    }

}
