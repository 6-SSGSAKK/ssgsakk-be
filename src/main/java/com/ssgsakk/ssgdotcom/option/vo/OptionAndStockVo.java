package com.ssgsakk.ssgdotcom.option.vo;

import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;


@Builder
@Getter
public class OptionAndStockVo {
    private Long productSeq;
    private List<HashMap.Entry<Long, String>> size;
    private List<HashMap.Entry<Long, String>> color;
    private List<HashMap.Entry<Long, String>> customizationOption;
}
