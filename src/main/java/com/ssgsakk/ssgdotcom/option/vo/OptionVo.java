package com.ssgsakk.ssgdotcom.option.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ssgsakk.ssgdotcom.option.dto.StockDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class OptionVo {
    private Integer depthLevel;
    private String firstDepthName;
    private String secondDepthName;
    private String thirdDepthName;
    private List<StockDto> options;
}
