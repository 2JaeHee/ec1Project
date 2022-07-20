package com.plateer.ec1.payment.vo.promotion.mapper;

import com.plateer.ec1.common.model.promotion.CcMbrPntModel;
import com.plateer.ec1.payment.vo.promotion.vo.req.PointReqVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PointTrxMapper {
    void savePointInfo(CcMbrPntModel ccMbrPntModel);
//
//    void saveUsePoint(CcMbrPntModel ccMbrPntModel);
//
//    void saveCancelPoint(CcMbrPntModel ccMbrPntModel);
}
