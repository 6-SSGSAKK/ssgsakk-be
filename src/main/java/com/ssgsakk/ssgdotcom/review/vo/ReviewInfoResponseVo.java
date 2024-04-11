package com.ssgsakk.ssgdotcom.review.vo;

import com.ssgsakk.ssgdotcom.contents.domain.ReviewContents;
import com.ssgsakk.ssgdotcom.contents.vo.ReviewContentsVo;
import com.ssgsakk.ssgdotcom.review.dto.ReviewInfoDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ReviewInfoResponseVo {
    private String reviewParagraph;
    private Short reviewScore;
    private List<ReviewContentsVo> reviewContentsList;
    private String purchaseProductOption;
    private String userId;
    private LocalDateTime reviewDate;

    public static ReviewInfoResponseVo DtoToVo(ReviewInfoDto reviewInfoDto) {
        return ReviewInfoResponseVo.builder()
                .reviewParagraph(reviewInfoDto.getReviewParagraph())
                .reviewScore(reviewInfoDto.getReviewScore())
                .reviewContentsList(reviewInfoDto.getReviewContentsList())
                .purchaseProductOption(reviewInfoDto.getPurchaseProductOption())
                .userId(reviewInfoDto.getUserId())
                .reviewDate(reviewInfoDto.getReviewDate())
                .build();
    }
}
