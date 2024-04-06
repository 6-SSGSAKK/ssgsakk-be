package com.ssgsakk.ssgdotcom.likes.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteProductLikesResponseVo {
    private Long productSeq;

    @Builder
    public DeleteProductLikesResponseVo(Long productSeq) {
        this.productSeq = productSeq;
    }
}
