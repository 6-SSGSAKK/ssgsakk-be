package com.ssgsakk.ssgdotcom.likes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class DeleteLikeProductDto {
    private String uuid;
    private List<Long> likeProductList;

    @Builder
    public DeleteLikeProductDto(String uuid, List<Long> likeProductList) {
        this.uuid = uuid;
        this.likeProductList = likeProductList;
    }
}
