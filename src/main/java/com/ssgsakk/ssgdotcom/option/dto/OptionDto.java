package com.ssgsakk.ssgdotcom.option.dto;

import lombok.*;

import java.util.AbstractMap;
import java.util.List;

@Builder
@Getter
public class OptionDto {
    private List<AbstractMap.SimpleEntry<Long, String>> size;
    private List<AbstractMap.SimpleEntry<Long, String>> color;
    private List<AbstractMap.SimpleEntry<Long, String>> customizationOption;
}
