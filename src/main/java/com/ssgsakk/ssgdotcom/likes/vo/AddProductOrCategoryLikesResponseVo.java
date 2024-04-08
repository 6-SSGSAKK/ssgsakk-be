package com.ssgsakk.ssgdotcom.likes.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddProductOrCategoryLikesResponseVo {
    private Long productSeq;
    private Long categorySeq;

    @Builder
    public AddProductOrCategoryLikesResponseVo(Long productSeq, Long categorySeq) {
        this.productSeq = productSeq;
        this.categorySeq = categorySeq;
    }
}
