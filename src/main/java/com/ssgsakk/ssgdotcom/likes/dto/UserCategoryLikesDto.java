package com.ssgsakk.ssgdotcom.likes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserCategoryLikesDto {
    private String uuid;

    @Builder
    public UserCategoryLikesDto(String uuid) {
        this.uuid = uuid;
    }
}
