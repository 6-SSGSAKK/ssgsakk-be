package com.ssgsakk.ssgdotcom.review.dto;


import com.ssgsakk.ssgdotcom.contents.vo.ReviewContentsVo;
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
    private List<ReviewContentsVo> reviewContentsVoList;
    private String purchaseProductOption;

    public static ReviewDto VoToDto(ReviewRequestVo reviewRequestVo) {
        return ReviewDto.builder()
                .reviewParagraph(reviewRequestVo.getReviewParagraph())
                .reviewScore(reviewRequestVo.getReviewScore())
                .reviewContentsVoList(reviewRequestVo.getReviewContentsVoList())
                .productSeq(reviewRequestVo.getProductSeq())
                .purchaseProductSeq(reviewRequestVo.getPurchaseProductSeq())
                .purchaseProductOption(reviewRequestVo.getPurchaseProductOption())
                .build();
    }
}
