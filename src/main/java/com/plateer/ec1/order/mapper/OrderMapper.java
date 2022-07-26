package com.plateer.ec1.order.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    String getOrdNo();
}
