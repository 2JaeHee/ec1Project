package com.plateer.ec1.order.mapper;

import com.plateer.ec1.common.model.order.OpOrdBase;
import com.plateer.ec1.common.model.order.OpPayInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderTrxMapper {
    void saveOrderBaseInfo(OpOrdBase opOrdBase);
}
