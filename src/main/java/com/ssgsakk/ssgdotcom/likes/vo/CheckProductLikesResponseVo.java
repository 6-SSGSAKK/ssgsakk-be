package com.ssgsakk.ssgdotcom.likes.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CheckProductLikesResponseVo {
    private Long productSeq;
    private Integer likeState;

    @Builder
    public CheckProductLikesResponseVo(Long productSeq, Integer likeState) {
        this.productSeq = productSeq;
        this.likeState = likeState;
    }
}
