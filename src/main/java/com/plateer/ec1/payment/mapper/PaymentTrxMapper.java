package com.plateer.ec1.payment.mapper;

import com.plateer.ec1.common.model.order.OpPayInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentTrxMapper {
    /**
     * 결제 정보 추가
     * @param basePayInfo
     */
    void savePayInfo(OpPayInfo basePayInfo);

    /**
     * 결제 - 결제완료처리
     * @param opPayInfo
     */
    void modifyPayInfo(OpPayInfo opPayInfo);

    /**
     * 결제 - 결제 취소 시, 결제금액, 환불금액 update
     * @param setModifyData
     */
    void modifyPayRefundAmt(OpPayInfo setModifyData);
}
