package com.ssgsakk.ssgdotcom.purchaseproduct.dto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PurchaseProductStateDto {

    private Integer purchaseProductState;


    @Builder
    public PurchaseProductStateDto(Integer purchaseProductState)
    {
        this.purchaseProductState = purchaseProductState;
    }
}
