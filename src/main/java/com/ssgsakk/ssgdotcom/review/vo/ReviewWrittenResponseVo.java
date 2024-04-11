package com.ssgsakk.ssgdotcom.review.vo;

import com.ssgsakk.ssgdotcom.review.dto.ReviewWrittenDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class ReviewWrittenResponseVo {
    private Long purchaseSeq;
    private Long purchaseProductSeq;
    private String purchaseCode;
    private LocalDateTime purchaseDate;
    private Long productSeq;
    private String purchaseProductName;
    private String purchaseProductOption;
    private Long reviewSeq;
    private Short reviewScore;
    private String reviewParagraph;
    private LocalDateTime reviewDate;

    public static List<ReviewWrittenResponseVo> DtoListToVoList(List<ReviewWrittenDto> reviewWrittenDtoList) {
        return reviewWrittenDtoList.stream().map(reviewWrittenDto -> ReviewWrittenResponseVo.builder()
                .purchaseSeq(reviewWrittenDto.getPurchaseSeq())
                .purchaseProductSeq(reviewWrittenDto.getPurchaseProductSeq())
                .purchaseCode(reviewWrittenDto.getPurchaseCode())
                .purchaseDate(reviewWrittenDto.getPurchaseDate())
                .productSeq(reviewWrittenDto.getProductSeq())
                .purchaseProductName(reviewWrittenDto.getPurchaseProductName())
                .purchaseProductOption(reviewWrittenDto.getPurchaseProductOption())
                .reviewSeq(reviewWrittenDto.getReviewSeq())
                .reviewScore(reviewWrittenDto.getReviewScore())
                .reviewParagraph(reviewWrittenDto.getReviewParagraph())
                .reviewDate(reviewWrittenDto.getReviewDate())
                .build())
                .collect(Collectors.toList());
    }
}
