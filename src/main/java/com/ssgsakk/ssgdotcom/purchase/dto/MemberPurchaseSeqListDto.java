package com.ssgsakk.ssgdotcom.purchase.dto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
public class MemberPurchaseSeqListDto {

    private Long purchaseSeq;
    private String uuid;


    public MemberPurchaseSeqListDto(Long purchaseSeq, String uuid) {
        this.purchaseSeq = purchaseSeq;
        this.uuid = uuid;
    }

}
