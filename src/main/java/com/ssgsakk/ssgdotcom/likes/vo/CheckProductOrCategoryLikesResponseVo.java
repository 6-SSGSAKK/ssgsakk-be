package com.ssgsakk.ssgdotcom.likes.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CheckProductOrCategoryLikesResponseVo {
    private Long productSeq;
    private Long categorySeq;
    private Integer likeState;

    @Builder
    public CheckProductOrCategoryLikesResponseVo(Long productSeq, Long categorySeq, Integer likeState) {
        this.productSeq = productSeq;
        this.categorySeq = categorySeq;
        this.likeState = likeState;
    }
}
