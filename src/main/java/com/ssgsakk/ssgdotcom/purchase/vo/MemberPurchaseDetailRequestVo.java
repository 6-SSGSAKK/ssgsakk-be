package com.ssgsakk.ssgdotcom.purchase.vo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberPurchaseDetailRequestVo {
    private Long purchaseSeq;

    public MemberPurchaseDetailRequestVo(Long purchaseSeq) {
        this.purchaseSeq = purchaseSeq;
    }
}
