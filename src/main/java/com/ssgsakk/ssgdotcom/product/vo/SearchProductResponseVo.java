package com.ssgsakk.ssgdotcom.product.vo;

import com.ssgsakk.ssgdotcom.product.dto.SearchProductDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class SearchProductResponseVo {
    private Long productSeq;

    public static List<SearchProductResponseVo> DtoToVo(List<SearchProductDto> searchProductDtoList) {
        return searchProductDtoList.stream()
                .map(searchProductDto -> SearchProductResponseVo.builder()
                        .productSeq(searchProductDto.getProductSeq())
                        .build())
                .collect(Collectors.toList());
    }
}