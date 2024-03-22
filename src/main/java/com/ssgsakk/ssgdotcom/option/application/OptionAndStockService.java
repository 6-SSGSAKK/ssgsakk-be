package com.ssgsakk.ssgdotcom.option.application;

import com.ssgsakk.ssgdotcom.option.dto.AddOptionDto;
import com.ssgsakk.ssgdotcom.option.dto.DeleteOptionDto;
import com.ssgsakk.ssgdotcom.option.dto.OptionAndStockDto;
import com.ssgsakk.ssgdotcom.option.dto.UpdateOptionDto;

import java.util.List;

public interface OptionAndStockService {
    List<OptionAndStockDto> findOptionsByProductId(Long productId);
    void addOptions(AddOptionDto addOptionDto);
    void deleteOption(Long optionId);
}
