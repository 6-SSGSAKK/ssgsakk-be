package com.ssgsakk.ssgdotcom.likes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserProductLikesDto {
    private String uuid;
    private Long folderSeq;

    @Builder
    public UserProductLikesDto(String uuid, Long folderSeq) {
        this.uuid = uuid;
        this.folderSeq = folderSeq;
    }
}
