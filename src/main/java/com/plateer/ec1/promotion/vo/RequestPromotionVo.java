package com.plateer.ec1.promotion.vo;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class RequestPromotionVo {
    private String mbrNo;
    private List<Product> productList;
}
