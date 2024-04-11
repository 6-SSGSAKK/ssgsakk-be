package com.ssgsakk.ssgdotcom.purchaseproduct.Vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PurchaseProductListRequestVo {
    private Long purchaseSeq;

    @Builder
    public PurchaseProductListRequestVo(Long purchaseSeq) {
        this.purchaseSeq = purchaseSeq;
    }
}
