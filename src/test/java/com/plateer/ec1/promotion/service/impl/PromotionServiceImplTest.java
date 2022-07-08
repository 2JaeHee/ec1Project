package com.plateer.ec1.promotion.service.impl;

import com.plateer.ec1.common.model.promotion.CcCpnBaseModel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PromotionServiceImplTest {

    @Test
    @DisplayName("쿠폰 다운로드 기간 체크")
    void periodValidate() {
        CcCpnBaseModel ccCpnBaseModel1 = CcCpnBaseModel.builder()
                .dwlAvlStrtDd("20220708")
                .dwlAvlEndDd("20220710")
                .build();
        Assertions.assertThat(ccCpnBaseModel1.periodValidate()).isTrue();

        CcCpnBaseModel ccCpnBaseModel2 = CcCpnBaseModel.builder()
                .dwlAvlStrtDd("20220601")
                .dwlAvlEndDd("20220630")
                .build();
        Assertions.assertThat(ccCpnBaseModel2.periodValidate()).isFalse();
    }

    @Test
    @DisplayName("쿠폰 다운로드 가능 수량 체크")
    void dwlPsbCntValid() {
        CcCpnBaseModel ccCpnBaseModel1 = CcCpnBaseModel.builder()
                .dwlPsbCnt(10)
                .build();
        Assertions.assertThat(ccCpnBaseModel1.isDwlPsbCntValid(5)).isTrue();

        CcCpnBaseModel ccCpnBaseModel2 = CcCpnBaseModel.builder()
                .dwlPsbCnt(1)
                .build();

        Assertions.assertThat(ccCpnBaseModel2.isDwlPsbCntValid(5)).isFalse();
    }

    @Test
    @DisplayName("개인별 쿠폰 다운로드 가능 수량")
    void psnDwlPsbCntValid() {
        CcCpnBaseModel ccCpnBaseModel1 = CcCpnBaseModel.builder()
                .psnDwlPsbCnt(10)
                .build();
        Assertions.assertThat(ccCpnBaseModel1.isPsnDwlPsbCntValid(5)).isTrue();

        CcCpnBaseModel ccCpnBaseModel2 = CcCpnBaseModel.builder()
                .psnDwlPsbCnt(1)
                .build();

        Assertions.assertThat(ccCpnBaseModel2.isPsnDwlPsbCntValid(5)).isFalse();
    }
}