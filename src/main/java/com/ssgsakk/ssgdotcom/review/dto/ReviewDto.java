package com.ssgsakk.ssgdotcom.review.dto;

import com.ssgsakk.ssgdotcom.contents.domain.Contents;
import com.ssgsakk.ssgdotcom.review.vo.ReviewVo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewDto {
    private Long reviewSeq;
    private Long productSeq;
    private String reviewParagraph;
    private Short reviewScore;
    private Contents contentsUrl;

    public static ReviewDto VoToDto(ReviewVo reviewVo) {
        return ReviewDto.builder()
                .reviewParagraph(reviewVo.getReviewParagraph())
                .reviewScore(reviewVo.getReviewScore())
                .contentsUrl(reviewVo.getContentsUrl())
                .build();
    }
}
