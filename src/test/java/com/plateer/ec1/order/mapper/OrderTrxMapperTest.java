package com.plateer.ec1.order.mapper;

import com.plateer.ec1.common.code.order.OPT0001Enum;
import com.plateer.ec1.common.code.order.OPT0002Enum;
import com.plateer.ec1.common.code.product.PRD0001Enum;
import com.plateer.ec1.common.code.product.PRD0002Enum;
import com.plateer.ec1.common.model.order.OpGoodsInfo;
import com.plateer.ec1.common.model.order.OpOrdBase;
import com.plateer.ec1.payment.enums.BankCode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderTrxMapperTest {

    @Autowired
    OrderTrxMapper orderTrxMapper;

    @Test
    @DisplayName("주문기본정보등록")
    void savePayInfo() {
        OpOrdBase opOrdBase = OpOrdBase.builder()
                .ordNo("O202207190001")
                .mbrNo("test01")
                .ordTpCd(OPT0001Enum.GENERAL.getCode())
                .ordSysCcd(OPT0002Enum.BO.getCode())
                .ordReqDtime(LocalDateTime.now())
                .ordCmtDtime(LocalDateTime.now())
                .ordNm("이재희")
                .ordSellNo("01011112222")
                .ordAddr("주문자주소")
                .ordAddrDtl("상세주소")
                .rfndAcctNo("111122223333")
                .rfndAcctOwnNm("이재희")
                .rfndBnkCk("03")
                .build();
        orderTrxMapper.saveOpOrdBase(opOrdBase);
    }

    @Test
    @DisplayName("주문상품정등록")
    void saveOpGoodsInfo() {
        OpGoodsInfo opGoodsInfo = OpGoodsInfo.builder()
                .ordNo("O202207190001")
                .ordGoodsNo("P001")
                .ordItemNo("1")
                .goodsSellTpCd(PRD0001Enum.GENERAL.getCode())
                .goodsDlvTpCd(PRD0002Enum.DIRECT_DELIVERY.getCode())
                .goodsNm("라운드반팔티")
                .itemNm("네이비")
                .build();
        orderTrxMapper.saveOpGoodsInfo(opGoodsInfo);
    }

    @Test
    void enumTest() {
        String code = "03";
        BankCode bankCode = BankCode.of(code);
        Assertions.assertThat(bankCode.getCode()).isEqualTo(code);
    }
}