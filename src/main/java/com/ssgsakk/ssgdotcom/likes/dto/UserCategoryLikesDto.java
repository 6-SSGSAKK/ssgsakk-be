package com.ssgsakk.ssgdotcom.likes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserCategoryLikesDto {
    private String uuid;
    private Long folderSeq;

    @Builder
    public UserCategoryLikesDto(String uuid, Long folderSeq) {
        this.uuid = uuid;
        this.folderSeq = folderSeq;
    }
}
