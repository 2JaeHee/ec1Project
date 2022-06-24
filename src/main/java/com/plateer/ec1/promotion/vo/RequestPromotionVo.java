package com.plateer.ec1.promotion.vo;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestPromotionVo {
    private String memberNo;
    private List<Product> productList;
    private Long couponIssueNo;
}
