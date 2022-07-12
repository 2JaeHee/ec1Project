package com.plateer.ec1.payment.mapper;

import com.plateer.ec1.common.model.order.OpPayInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentTrxMapper {
    void savePayInfo(OpPayInfo basePayInfo);
}
