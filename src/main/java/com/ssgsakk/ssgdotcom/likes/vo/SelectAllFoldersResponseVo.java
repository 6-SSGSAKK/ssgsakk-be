package com.ssgsakk.ssgdotcom.likes.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SelectAllFoldersResponseVo {
    private Long folderSeq;
    private String folderName;
    private String folderThumbnailUrl;

    @Builder
    public SelectAllFoldersResponseVo(Long folderSeq, String folderName, String folderThumbnailUrl) {
        this.folderSeq = folderSeq;
        this.folderName = folderName;
        this.folderThumbnailUrl = folderThumbnailUrl;
    }
}
