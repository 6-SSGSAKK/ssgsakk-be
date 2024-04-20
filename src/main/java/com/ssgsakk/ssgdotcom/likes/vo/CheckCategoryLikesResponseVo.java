package com.ssgsakk.ssgdotcom.likes.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CheckCategoryLikesResponseVo {
    private Long categorySeq;
    private Integer likeState;

    @Builder
    public CheckCategoryLikesResponseVo(Long categorySeq, Integer likeState) {
        this.categorySeq = categorySeq;
        this.likeState = likeState;
    }
}
