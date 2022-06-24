package com.plateer.ec1.claim.factory.impl;

import com.plateer.ec1.claim.enums.ClaimProcessorType;
import com.plateer.ec1.claim.factory.ClaimProcessor;
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
//    private final ClaimDataCreator claimDataCreator;
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
        log.info("[AcceptWithdrawalProcessor.doProcess] 클레임 접수/철회 프로세서");
        Long logNo = monitoringLogHelper.insertMonitoringLog("1");

        doValidationProcess(claimDto);

        //claimDataCreator.saveData(claimDto);

        verifyAmount(claimDto);

        monitoringLogHelper.updateMonitortingLog(logNo, "complete");

    }


}
