package com.plateer.ec1.payment.cancel;

import com.plateer.ec1.common.code.order.OPT0009Enum;
import com.plateer.ec1.common.code.order.OPT0010Enum;
import com.plateer.ec1.common.code.order.OPT0011Enum;
import com.plateer.ec1.common.model.order.OpPayInfo;
import com.plateer.ec1.payment.mapper.PaymentMapper;
import com.plateer.ec1.payment.mapper.PaymentTrxMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InicisCancelGubun {
    @Autowired
    PaymentMapper paymentMapper;

    @Autowired
    PaymentTrxMapper paymentTrxMapper;

    void modifyPayCancelData() {
        //주문번호
        String ordNo = "O202207120002";
        long cncl = 500;

        OpPayInfo orderPayInfo = paymentMapper.getOrderPayInfo(ordNo);
        long rfndAvlAmt = orderPayInfo.getPayAmt() - cncl;
        //해당 데이터에 취소금액 환불금액 update
        OpPayInfo setModifyData = OpPayInfo.builder().payNo(orderPayInfo.getPayNo()).cnclAmt(cncl).rfndAvlAmt(rfndAvlAmt).build();
        paymentTrxMapper.modifyPayRefundAmt(setModifyData);
    }


    void cancelGubun() {

        //주문번호
        String ordNo = "O202207120002";
        String climNo = "1234";
        long cncl = 500;

        OpPayInfo orderPayInfo = paymentMapper.getOrderPayInfo(ordNo);

        if (OPT0011Enum.REQUEST.getCode().equals(orderPayInfo.getPayPrgsScd())){
            //입금전
            //전체취소 API 호출
            if (orderPayInfo.getRfndAvlAmt() > cncl) {
                //남은금액에 대해 결제 요청 API 호출
            }

        } else if (OPT0011Enum.COMPLETE.getCode().equals(orderPayInfo.getPayPrgsScd())) {
            //입금후
            if (orderPayInfo.getRfndAvlAmt() == cncl) {
                //전체취소 API 호출
            } else {
                //부분취소 API 호출
            }


        }

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
