package com.ssgsakk.ssgdotcom.review.dto;

import com.ssgsakk.ssgdotcom.review.vo.UpdateReviewRequestVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UpdateReviewDto {
    private Long reviewSeq;
    private String reviewParagraph;
    private Short reviewScore;
    private List<String> contentsUrl;

    public static UpdateReviewDto VoToDto(Long reviewSeq, UpdateReviewRequestVo updateReviewRequestVo) {
        return UpdateReviewDto.builder()
                .reviewSeq(reviewSeq)
                .reviewParagraph(updateReviewRequestVo.getReviewParagraph())
                .reviewScore(updateReviewRequestVo.getReviewScore())
                .contentsUrl(updateReviewRequestVo.getContentsUrl())
                .build();
    }
}
