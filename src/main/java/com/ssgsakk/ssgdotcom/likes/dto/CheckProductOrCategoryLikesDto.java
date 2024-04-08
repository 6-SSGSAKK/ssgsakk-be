package com.ssgsakk.ssgdotcom.likes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CheckProductOrCategoryLikesDto {
    private String uuid;
    private Long productSeq;
    private Long categorySeq;
    private Integer likeState;

    @Builder
    public CheckProductOrCategoryLikesDto(String uuid, Long productSeq, Long categorySeq, Integer likeState) {
        this.uuid = uuid;
        this.productSeq = productSeq;
        this.categorySeq = categorySeq;
        this.likeState = likeState;
    }
}
