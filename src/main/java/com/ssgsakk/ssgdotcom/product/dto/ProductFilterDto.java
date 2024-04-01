package com.ssgsakk.ssgdotcom.product.dto;

import com.ssgsakk.ssgdotcom.common.util.DeliveryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductFilterDto {
    private DeliveryType deliveryType;
    private int minPrice;
    private int maxPrice;
}
