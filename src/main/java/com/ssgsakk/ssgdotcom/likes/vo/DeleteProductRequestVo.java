package com.ssgsakk.ssgdotcom.likes.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class DeleteProductRequestVo {
    private List<Long> productSeqs;
    
    @Builder
    public DeleteProductRequestVo(List<Long> productSeqs) {
        this.productSeqs = productSeqs;
    }
}
