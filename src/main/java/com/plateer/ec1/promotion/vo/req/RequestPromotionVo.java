package com.plateer.ec1.promotion.vo.req;


import com.plateer.ec1.promotion.vo.Product;
import com.plateer.ec1.promotion.enums.PromotionType;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestPromotionVo {
    @NotNull
    private String mbrNo;
    @NotNull
    private List<Product> productList;

    private PromotionType type;


}
