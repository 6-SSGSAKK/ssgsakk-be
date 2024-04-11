package com.ssgsakk.ssgdotcom.review.vo;

import com.ssgsakk.ssgdotcom.contents.vo.ReviewContentsVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UpdateReviewRequestVo {
    private String reviewParagraph;
    private Short reviewScore;
    private List<ReviewContentsVo> reviewContentsVoList;
}
