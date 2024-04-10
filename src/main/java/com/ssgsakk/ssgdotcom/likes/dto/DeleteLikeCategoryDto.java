package com.ssgsakk.ssgdotcom.likes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class DeleteLikeCategoryDto {
    private String uuid;
    private List<Long> likeCategoryList;

    @Builder
    public DeleteLikeCategoryDto(String uuid, List<Long> likeCategoryList) {
        this.uuid = uuid;
        this.likeCategoryList = likeCategoryList;
    }
}
