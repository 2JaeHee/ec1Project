package com.plateer.ec1.product.mapper;

import com.plateer.ec1.product.vo.PrGoodsBaseVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {

    PrGoodsBaseVo getPrGoodsBaseInfo(String goodsNo);

}
