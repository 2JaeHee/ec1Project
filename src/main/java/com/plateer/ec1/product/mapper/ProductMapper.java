package com.plateer.ec1.product.mapper;

import com.plateer.ec1.payment.vo.promotion.vo.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> getGoodsBaseInfo(List<Product> productList);
}
