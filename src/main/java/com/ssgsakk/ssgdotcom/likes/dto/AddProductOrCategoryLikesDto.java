package com.ssgsakk.ssgdotcom.likes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class AddProductOrCategoryLikesDto {
    private String uuid;
    private Long productSeq;
    private Long categorySeq;

    @Builder
    public AddProductOrCategoryLikesDto(String uuid, Long productSeq, Long categorySeq) {
        this.uuid = uuid;
        this.productSeq = productSeq;
        this.categorySeq = categorySeq;
    }
}
