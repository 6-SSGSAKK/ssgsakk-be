package com.ssgsakk.ssgdotcom.review.dto;


import com.ssgsakk.ssgdotcom.review.vo.ReviewVo;
import lombok.Builder;
import lombok.Getter;


import java.util.List;

@Getter
@Builder

public class ReviewDto {
    private Long productSeq;
    private String reviewParagraph;
    private Short reviewScore;
    private List<String> contentsUrl;

    public static ReviewDto VoToDto(ReviewVo reviewVo) {
        return ReviewDto.builder()
                .reviewParagraph(reviewVo.getReviewParagraph())
                .reviewScore(reviewVo.getReviewScore())
                .contentsUrl(reviewVo.getContentsUrl())
                .build();
    }
}
