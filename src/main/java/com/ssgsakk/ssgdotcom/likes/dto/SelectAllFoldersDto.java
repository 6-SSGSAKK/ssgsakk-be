package com.ssgsakk.ssgdotcom.likes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SelectAllFoldersDto {
    private String uuid;

    @Builder
    public SelectAllFoldersDto(String uuid) {
        this.uuid = uuid;
    }
}
