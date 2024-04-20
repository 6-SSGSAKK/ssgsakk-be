package com.ssgsakk.ssgdotcom.purchase.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NonMemberPurchaseResponseVo {
    private Long purchaseSeq;


    @Builder
    public NonMemberPurchaseResponseVo(Long purchaseSeq) {
        this.purchaseSeq = purchaseSeq;
    }
}
