package com.plateer.ec1.payment.service;

import com.plateer.ec1.common.model.order.OpPayInfo;
import com.plateer.ec1.payment.mapper.PaymentTrxMapper;

public interface PaymentBizService {
    /**
     * 주문결제테이블 정보 저장
     * @param opPayInfo
     */
    void savePayInfo(OpPayInfo opPayInfo);

    /**
     * 주문결제테이블 수정
     * @param opPayInfo
     */
    void modifyPayInfo(OpPayInfo opPayInfo);

    /**
     * tid로 결제정보 조회
     * @param trsnId
     * @return
     */
    OpPayInfo getPayInfo(String trsnId);

    OpPayInfo getOrderPayInfo(String ordNo);

    void modifyPayRefundAmt(OpPayInfo opPayInfo);
}
