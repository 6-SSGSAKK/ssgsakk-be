package com.ssgsakk.ssgdotcom.review.dto;

import com.ssgsakk.ssgdotcom.contents.vo.ReviewContentsVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ReviewListDto {
    private Long reviewSeq;
    private String reviewParagraph;
    private Short reviewScore;
    private List<ReviewContentsVo> reviewContentsList;
    private String purchaseProductOption;
    private String userId;
    private LocalDateTime reviewDate;
}
