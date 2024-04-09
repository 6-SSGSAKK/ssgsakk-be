package com.ssgsakk.ssgdotcom.likes.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SelectAllFoldersThumbNailRequestVo {
    private Long likeFolderSeq;

    @Builder
    public SelectAllFoldersThumbNailRequestVo(Long likeFolderSeq) {
        this.likeFolderSeq = likeFolderSeq;
    }
}
