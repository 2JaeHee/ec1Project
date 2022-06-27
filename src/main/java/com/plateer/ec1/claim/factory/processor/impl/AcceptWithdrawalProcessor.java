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

//접수 / 철회
@Slf4j
@Service
@RequiredArgsConstructor
public class AcceptWithdrawalProcessor implements ClaimProcessor {
    private final ClaimValidator claimValidator;
    private final MonitoringLogHelper monitoringLogHelper;

    @Override
    public ClaimProcessorType getType() {
        return ClaimProcessorType.ACCEPT_WITHDRAWAL;
    }

    @Override
    public void doValidationProcess(ClaimDto claimDto) {
        claimValidator.isValidStatus(claimDto);
        claimValidator.isValidAmount(claimDto);
        log.info("[AcceptWithdrawalProcessor.doValidationProcess] doValidationProcess");
    }

    @Override
    public void verifyAmount(ClaimDto claimDto) {
        claimValidator.verifyAmount(claimDto);
        log.info("[AcceptWithdrawalProcessor.verifyAmount] verifyAmount");
    }

    @Override
    public void doProcess(ClaimDto claimDto) {
        ClaimDataCreator claimDataCreator = CreatorFactory.getCreator(claimDto);

        log.info("----------- [AcceptWithdrawalProcessor.doProcess] 클레임 접수/철회 프로세서");
        log.info("----------- [AcceptWithdrawalProcessor.doProcess] 모니터링 insert");
        Long logNo = monitoringLogHelper.insertMonitoringLog("1");

        log.info("----------- [AcceptWithdrawalProcessor.doProcess] claim validation 체크");
        doValidationProcess(claimDto);

        log.info("----------- [AcceptWithdrawalProcessor.doProcess] 클레임 data insert");
        claimDataCreator.getInsertClaimData(claimDto);
        claimDataCreator.getUpdateClaimData(claimDto);

        log.info("----------- [AcceptWithdrawalProcessor.doProcess] 금액검증");
        verifyAmount(claimDto);

        log.info("----------- [AcceptWithdrawalProcessor.doProcess] 모니터링 update");
        monitoringLogHelper.updateMonitortingLog(logNo, "complete");

    }


}
