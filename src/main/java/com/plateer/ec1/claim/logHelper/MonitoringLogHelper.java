package com.plateer.ec1.claim.logHelper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MonitoringLogHelper {
    public Long insertMonitoringLog(String logNo) {
        log.info("[MonitoringLogHelper.insertMonitoringLog]");
        return 1L;
    }

    public void updateMonitortingLog(Long logNo, String logStr) {
        log.info("[MonitoringLogHelper.updateMonitortingLog]");
    }
}
