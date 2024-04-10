package com.ssgsakk.ssgdotcom.likes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class DeleteProductDto {
    private String uuid;
    private List<Long> productSeqs;

    @Builder
    public DeleteProductDto(String uuid, List<Long> productSeqs) {
        this.uuid = uuid;
        this.productSeqs = productSeqs;
    }
}
