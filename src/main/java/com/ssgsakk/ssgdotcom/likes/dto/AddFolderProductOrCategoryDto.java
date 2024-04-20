package com.ssgsakk.ssgdotcom.likes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class AddFolderProductOrCategoryDto {
    private Long likeFolderSeq;
    private Long likeProductSeq;
    private Long likeCategorySeq;

    @Builder
    public AddFolderProductOrCategoryDto(Long likeFolderSeq, Long likeProductSeq, Long likeCategorySeq) {
        this.likeFolderSeq = likeFolderSeq;
        this.likeProductSeq = likeProductSeq;
        this.likeCategorySeq = likeCategorySeq;
    }

}
