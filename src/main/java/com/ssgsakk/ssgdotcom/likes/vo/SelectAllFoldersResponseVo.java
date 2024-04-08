package com.ssgsakk.ssgdotcom.likes.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SelectAllFoldersResponseVo {
    private Long likeFolderSeq;
    private String likeFolderName;

    @Builder
    public SelectAllFoldersResponseVo(Long likeFolderSeq, String likeFolderName) {
        this.likeFolderSeq = likeFolderSeq;
        this.likeFolderName = likeFolderName;
    }
}
