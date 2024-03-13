package com.ssgsakk.ssgdotcom.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchProductDto {
    private String productSeq;
    private String productName;
    private Integer productPrice;
    private String vendor;
    private String productCode;
    private Double discountPercent;
//    private String contentUrl;
//    private String stock;
//    private String category;
}
