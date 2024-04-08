package com.ssgsakk.ssgdotcom.product.dto;

import lombok.*;

import java.util.List;
import java.util.stream.Collectors;


@Builder
@Getter
public class SearchProductDto {
    private Long productSeq;

    public static List<SearchProductDto> ToDto(List<Long> productSeq) {
        return productSeq.stream()
                .map(product -> SearchProductDto.builder()
                        .productSeq(product)
                        .build())
                .collect(Collectors.toList());
    }
}
