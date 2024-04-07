package com.ssgsakk.ssgdotcom.likes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteProductOrCategoryLikesDto {
    private String uuid;
    private Long productSeq;
    private Long categorySeq;

    @Builder
    public DeleteProductOrCategoryLikesDto(String uuid, Long productSeq, Long categorySeq) {
        this.uuid = uuid;
        this.productSeq = productSeq;
        this.categorySeq = categorySeq;
    }
}
