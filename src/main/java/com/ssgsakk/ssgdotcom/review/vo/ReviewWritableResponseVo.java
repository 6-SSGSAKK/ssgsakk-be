package com.ssgsakk.ssgdotcom.review.vo;

import com.ssgsakk.ssgdotcom.review.dto.ReviewWritableDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class ReviewWritableResponseVo {
    private Long purchaseSeq;
    private Long purchaseProductSeq;
    private String purchaseCode;
    private LocalDateTime purchaseDate;
    private Long productSeq;
    private String purchaseProductName;
    private String purchaseProductOption;

    public static List<ReviewWritableResponseVo> DtoListToVoList(List<ReviewWritableDto> reviewWritableDtoList) {
        return reviewWritableDtoList.stream().map(reviewWritableDto -> ReviewWritableResponseVo.builder()
                .purchaseSeq(reviewWritableDto.getPurchaseSeq())
                .purchaseProductSeq(reviewWritableDto.getPurchaseProductSeq())
                .purchaseCode(reviewWritableDto.getPurchaseCode())
                .purchaseDate(reviewWritableDto.getPurchaseDate())
                .productSeq(reviewWritableDto.getProductSeq())
                .purchaseProductName(reviewWritableDto.getPurchaseProductName())
                .purchaseProductOption(reviewWritableDto.getPurchaseProductOption())
                .build())
                .collect(Collectors.toList());
    }
}
