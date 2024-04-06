package com.ssgsakk.ssgdotcom.likes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteProductLikesDto {
    private String uuid;
    private Long productSeq;

    @Builder
    public DeleteProductLikesDto(String uuid, Long productSeq) {
        this.uuid = uuid;
        this.productSeq = productSeq;
    }
}
