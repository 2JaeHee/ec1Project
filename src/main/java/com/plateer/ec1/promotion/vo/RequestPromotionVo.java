package com.plateer.ec1.promotion.vo;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Getter
public class RequestPromotionVo {
    private String mbrNo;
    private List<Product> productList;
}
