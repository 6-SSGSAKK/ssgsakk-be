package com.ssgsakk.ssgdotcom.purchase.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UpdateCancelltionStatusRequestVo {

    private Long purchaseSeq;
    private Boolean cancelltionStatus;

}
