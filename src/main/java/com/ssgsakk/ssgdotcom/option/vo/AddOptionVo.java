package com.ssgsakk.ssgdotcom.option.vo;

import com.ssgsakk.ssgdotcom.option.domain.Color;
import com.ssgsakk.ssgdotcom.option.domain.CustomizationOption;
import com.ssgsakk.ssgdotcom.option.domain.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddOptionVo {
    private Long productSeq;
    private Long sizeSeq;
    private Long colorSeq;
    private Long customizationOptionSeq;
    private Integer stock;
    private Integer minimumStock;
}
