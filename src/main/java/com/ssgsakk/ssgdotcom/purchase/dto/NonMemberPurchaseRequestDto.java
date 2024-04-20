package com.ssgsakk.ssgdotcom.purchase.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class NonMemberPurchaseRequestDto {

    private Long purchaseSeq;

    @Builder
    public NonMemberPurchaseRequestDto(Long purchaseSeq) {
        this.purchaseSeq = purchaseSeq;
    }
}
