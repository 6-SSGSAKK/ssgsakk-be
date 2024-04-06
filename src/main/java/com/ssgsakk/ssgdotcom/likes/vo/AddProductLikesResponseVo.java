package com.ssgsakk.ssgdotcom.likes.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddProductLikesResponseVo {
    private Long productSeq;

    @Builder
    public AddProductLikesResponseVo(Long productSeq) {
        this.productSeq = productSeq;
    }
}
