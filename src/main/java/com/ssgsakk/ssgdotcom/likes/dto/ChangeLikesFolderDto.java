package com.ssgsakk.ssgdotcom.likes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChangeLikesFolderDto {
    private String uuid;
    private Long folderSeq;
    private String folderName;

    @Builder
    public ChangeLikesFolderDto(String uuid, Long folderSeq, String folderName) {
        this.uuid = uuid;
        this.folderSeq = folderSeq;
        this.folderName = folderName;
    }
}
