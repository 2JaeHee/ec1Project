package com.plateer.ec1.payment.mapper;

import com.plateer.ec1.common.model.order.OpPayInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {
    OpPayInfo getPayInfo(String trsnId);

    OpPayInfo getOrderPayInfo(String ordNo);
}
