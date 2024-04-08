package com.ssgsakk.ssgdotcom.likes.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class UserCategoryLikesResponseVo {
    private Long categorySeq;
    private String categoryName;

    @Builder
    public UserCategoryLikesResponseVo(Long categorySeq, String categoryName) {
        this.categorySeq = categorySeq;
        this.categoryName = categoryName;
    }
}
