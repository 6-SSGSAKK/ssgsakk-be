package com.ssgsakk.ssgdotcom.likes.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class UserCategoryLikesResponseVo {
    private Long likeCategorySeq;
    private Long categorySeq;
    private String categoryName;

    @Builder
    public UserCategoryLikesResponseVo(Long likeCategorySeq, Long categorySeq, String categoryName) {
        this.likeCategorySeq = likeCategorySeq;
        this.categorySeq = categorySeq;
        this.categoryName = categoryName;
    }
}
