package com.plateer.ec1.promotion.service.impl;

import com.plateer.ec1.common.model.promotion.CcPrmBaseModel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PromotionExternalServiceImplTest {

    @Test
    @DisplayName("쿠폰 사용 복구 기간 체크 - (취소일시  < 프로모션종료일시 )")
    void periodValidate() {
        LocalDateTime endDt1 = LocalDateTime.of(2022, 9, 15, 0, 0);
        CcPrmBaseModel ccPrmBaseModel1 = CcPrmBaseModel.builder()
                .prmEndDt(endDt1)
                .build();

        Assertions.assertThat(ccPrmBaseModel1.periodValidate()).isTrue();

        LocalDateTime endDt2 = LocalDateTime.of(2022, 5, 31, 0, 0);
        CcPrmBaseModel ccPrmBaseModel2 = CcPrmBaseModel.builder()
                .prmEndDt(endDt2)
                .build();

        Assertions.assertThat(ccPrmBaseModel2.periodValidate()).isFalse();
    }

    @Test
    @DisplayName("쿠폰 사용 복구 기준일 체크 - (쿠폰발급 수정일 + 프로모션 기준일 < 프로모션종료일시 )")
    void referenceDateValidate() {
        LocalDateTime paramDate1 = LocalDateTime.now().minusDays(10);
        CcPrmBaseModel ccPrmBaseModel1 = CcPrmBaseModel.builder()
                .prmStdDd(5)
                .prmEndDt(LocalDateTime.now())
                .build();

        Assertions.assertThat(ccPrmBaseModel1.referenceDateValidate(paramDate1)).isTrue();

        LocalDateTime paramDate2 = LocalDateTime.now().minusDays(3);
        CcPrmBaseModel ccPrmBaseModel2 = CcPrmBaseModel.builder()
                .prmStdDd(5)
                .prmEndDt(LocalDateTime.now())
                .build();

        Assertions.assertThat(ccPrmBaseModel2.referenceDateValidate(paramDate2)).isFalse();
    }
}