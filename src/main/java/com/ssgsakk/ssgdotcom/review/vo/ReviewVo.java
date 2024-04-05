package com.ssgsakk.ssgdotcom.review.vo;

import com.ssgsakk.ssgdotcom.contents.domain.Contents;
import com.ssgsakk.ssgdotcom.review.dto.ReviewDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewVo {
    private Long productSeq;
    private String reviewParagraph;
    private Short reviewScore;
    private Contents contentsUrl;

}
