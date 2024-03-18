package com.ssgsakk.ssgdotcom.product.Vo;

import com.ssgsakk.ssgdotcom.product.dto.ProductDto;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
public class SearchProductResponseVo {
    private Long productSeq;
    private String productName;
    private Integer productPrice;
}