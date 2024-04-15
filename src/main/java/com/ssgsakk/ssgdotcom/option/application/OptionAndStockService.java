package com.ssgsakk.ssgdotcom.option.application;

import com.ssgsakk.ssgdotcom.option.dto.OptionDto;
import com.ssgsakk.ssgdotcom.option.dto.StockDto;

import java.util.List;

public interface OptionAndStockService {
    OptionDto getOptionList(Long productSeq);
    List<StockDto> getStocks(Long productSeq, Integer depthLevel);

}
