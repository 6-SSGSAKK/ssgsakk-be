package com.ssgsakk.ssgdotcom.purchase.vo;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class UpdateCancelltionStatusRequestVo {

    private Long purchaseSeq;
    private Boolean cancelltionStatus;

}
