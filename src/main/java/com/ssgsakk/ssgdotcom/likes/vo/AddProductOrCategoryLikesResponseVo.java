package com.ssgsakk.ssgdotcom.likes.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddProductOrCategoryLikesResponseVo {
    private Long productSeq;

    @Builder
    public AddProductOrCategoryLikesResponseVo(Long productSeq) {
        this.productSeq = productSeq;
    }
}
