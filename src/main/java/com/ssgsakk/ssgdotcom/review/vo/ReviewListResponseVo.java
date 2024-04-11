package com.ssgsakk.ssgdotcom.review.vo;

import com.ssgsakk.ssgdotcom.contents.vo.ReviewContentsVo;
import com.ssgsakk.ssgdotcom.review.dto.ReviewListDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class ReviewListResponseVo {
    private Long reviewSeq;
    private String reviewParagraph;
    private Short reviewScore;
    private List<ReviewContentsVo> reviewContentsVoList;
    private String purchaseProductOption;
    private String userId;
    private LocalDateTime reviewDate;

    public static List<ReviewListResponseVo> DtoListToVoList(List<ReviewListDto> reviewListDto) {
        return reviewListDto.stream().map(reviewList -> ReviewListResponseVo.builder()
                .reviewSeq(reviewList.getReviewSeq())
                .reviewParagraph(reviewList.getReviewParagraph())
                .reviewScore(reviewList.getReviewScore())
                .reviewContentsVoList(reviewList.getReviewContentsVoList())
                .purchaseProductOption(reviewList.getPurchaseProductOption())
                .userId(reviewList.getUserId())
                .reviewDate(reviewList.getReviewDate())
                .build()).collect(Collectors.toList());
    }
}
