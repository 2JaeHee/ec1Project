package com.plateer.ec1.order.mapper;

import com.plateer.ec1.common.model.order.OpOrdClmMntLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderClaimLogTrxMapper {
    void saveCreateLog(OpOrdClmMntLog opOrdClmMntLog);
}
