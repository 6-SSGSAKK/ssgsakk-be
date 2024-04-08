package com.ssgsakk.ssgdotcom.likes.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteProductOrCategoryLikesResponseVo {
    private Long productSeq;
    private Long categorySeq;
    private String likeState;

    @Builder
    public DeleteProductOrCategoryLikesResponseVo(Long productSeq, Long categorySeq, String likeState) {
        this.productSeq = productSeq;
        this.categorySeq = categorySeq;
        this.likeState = likeState;
    }
}
