package com.plateer.ec1.payment.cancel;

import com.plateer.ec1.common.code.order.OPT0009Enum;
import com.plateer.ec1.common.code.order.OPT0010Enum;
import com.plateer.ec1.common.code.order.OPT0011Enum;
import com.plateer.ec1.common.model.order.OpPayInfo;
import com.plateer.ec1.payment.mapper.PaymentMapper;
import com.plateer.ec1.payment.mapper.PaymentTrxMapper;
import com.plateer.ec1.payment.vo.OrderPayInfoReq;
import com.plateer.ec1.payment.vo.OrderPayInfoRes;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class InicisCancelGubunTest {
    @Autowired
    PaymentMapper paymentMapper;

    @Autowired
    PaymentTrxMapper paymentTrxMapper;

    void modifyPayCancelData() {
        //주문번호
        String ordNo = "O202207120002";
        long cncl = 500;

        OrderPayInfoRes orderPayInfo = paymentMapper.getOrderPayInfo(OrderPayInfoReq.inicis(ordNo));
        long rfndAvlAmt = orderPayInfo.getPayAmt() - cncl;
        //해당 데이터에 취소금액 환불금액 update
        OpPayInfo setModifyData = OpPayInfo.builder().payNo(orderPayInfo.getPayNo()).cnclAmt(cncl).rfndAvlAmt(rfndAvlAmt).build();
        paymentTrxMapper.modifyPayRefundAmt(setModifyData);
    }

    @Test
    @DisplayName("입금전/후, 전체환불/부분환불 API 구분 체크")
    void cancelGubun() {

        //주문번호
        String ordNo = "O202207190001";
        String climNo = "1234";
        long cncl = 500;

        OrderPayInfoRes orderPayInfo = paymentMapper.getOrderPayInfo(OrderPayInfoReq.inicis(ordNo));

        String gubunCode = "TEST";
        if (OPT0011Enum.REQUEST.getCode().equals(orderPayInfo.getPayPrgsScd())){
            //입금전
            //전체취소 API 호출
            log.info("입금전 - 전체환불");
            gubunCode = "BEFORE_ALL_REFUND";
            if (orderPayInfo.getRfndAvlAmt() > cncl) {
                //남은금액에 대해 결제 요청 API 호출
                log.info("입금전 - 남은금액이 있을 시 결제요청 API call");
                gubunCode = "BEFORE_ALL_REFUND_PAY_CALL";
            }

        } else if (OPT0011Enum.COMPLETE.getCode().equals(orderPayInfo.getPayPrgsScd())) {
            //입금후
            if (orderPayInfo.getRfndAvlAmt() == cncl) {
                //전체취소 API 호출
                log.info("입금후 - 전체환불");
                gubunCode = "AFTER_ALL_REFUND";
            } else {
                //부분취소 API 호출
                log.info("입금후 - 부분환불");
                gubunCode = "AFTER_PARTIAL_REFUND";
            }
        }
        Assertions.assertThat(gubunCode).isEqualTo("BEFORE_ALL_REFUND_PAY_CALL");
    }

    @Test
    void cancelCall() {
        //주문번호
        String ordNo = "O202207190001";
        String climNo = "1234";
        long cncl = 500;

        OrderPayInfoRes orderPayInfo = paymentMapper.getOrderPayInfo(OrderPayInfoReq.inicis(ordNo));
        //취소 API call
        //결과값
        String trsnId = "1234";
        String vrAcct = "12341234";
        String vrAcctNm = "이재희";
        String vrBnkCd = "03";

        //취소완료 insert
        OpPayInfo saveCancelCompleteData = OpPayInfo.builder()
                .ordNo(orderPayInfo.getOrdNo())
                .clmNo(climNo)
                .payMnCd(OPT0009Enum.VIRTUAL_ACCOUNT.getCode())
                .payPrgsScd(OPT0010Enum.CANCEL.getCode())
                .payAmt(cncl)
                .trsnId(trsnId)
                .orgPayNo(orderPayInfo.getPayNo())
                .vrAcct(vrAcct)
                .vrAcctNm(vrAcctNm)
                .vrBnkCd(vrBnkCd)
                .build();

        paymentTrxMapper.savePayInfo(saveCancelCompleteData);
    }
}
