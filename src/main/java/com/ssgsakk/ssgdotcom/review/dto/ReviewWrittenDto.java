package com.ssgsakk.ssgdotcom.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewWrittenDto {
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
}
