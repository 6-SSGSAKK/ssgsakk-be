package com.ssgsakk.ssgdotcom.likes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddLikesFolderDto {
    private String uuid;
    private String folderName;

    @Builder
    public AddLikesFolderDto(String uuid, String folderName) {
        this.uuid = uuid;
        this.folderName = folderName;
    }
}
