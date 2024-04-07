package com.ssgsakk.ssgdotcom.likes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserProductLikesDto {
    private String uuid;

    @Builder
    public UserProductLikesDto(String uuid) {
        this.uuid = uuid;
    }
}
