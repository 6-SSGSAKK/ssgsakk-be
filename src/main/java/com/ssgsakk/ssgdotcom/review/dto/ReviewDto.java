package com.ssgsakk.ssgdotcom.review.dto;


import com.ssgsakk.ssgdotcom.review.vo.ReviewRequestVo;
import lombok.Builder;
import lombok.Getter;


import java.util.List;

@Getter
@Builder

public class ReviewDto {
    private Long purchaseProductSeq;
    private Long productSeq;
    private String reviewParagraph;
    private Short reviewScore;
    private List<String> contentsUrl;
    private String purchaseProductOption;

    public static ReviewDto VoToDto(ReviewRequestVo reviewRequestVo) {
        return ReviewDto.builder()
                .reviewParagraph(reviewRequestVo.getReviewParagraph())
                .reviewScore(reviewRequestVo.getReviewScore())
                .contentsUrl(reviewRequestVo.getContentsUrl())
                .productSeq(reviewRequestVo.getProductSeq())
                .purchaseProductSeq(reviewRequestVo.getPurchaseProductSeq())
                .purchaseProductOption(reviewRequestVo.getPurchaseProductOption())
                .build();
    }
}
