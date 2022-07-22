package com.plateer.ec1.payment.mapper;

import com.plateer.ec1.common.model.order.OpPayInfo;
import com.plateer.ec1.payment.vo.OrderPayInfoReq;
import com.plateer.ec1.payment.vo.OrderPayInfoRes;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {
    OpPayInfo getPayInfo(String trsnId);

    OrderPayInfoRes getOrderPayInfo(OrderPayInfoReq orderPayInfoReq);
}
