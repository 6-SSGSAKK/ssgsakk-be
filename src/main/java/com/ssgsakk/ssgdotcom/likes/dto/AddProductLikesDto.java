package com.ssgsakk.ssgdotcom.likes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class AddProductLikesDto {
    private String uuid;
    private Long productSeq;

    @Builder
    public AddProductLikesDto(String uuid, Long productSeq) {
        this.uuid = uuid;
        this.productSeq = productSeq;
    }
}
