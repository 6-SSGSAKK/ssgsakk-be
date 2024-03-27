package com.ssgsakk.ssgdotcom.option.dto;

import lombok.*;

import java.util.HashMap;
import java.util.List;

@Builder
@Getter
public class OptionDto {
    private List<HashMap.Entry<Long, String>> size;
    private List<HashMap.Entry<Long, String>> color;
    private List<HashMap.Entry<Long, String>> customizationOption;
}
