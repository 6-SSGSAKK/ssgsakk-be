package com.ssgsakk.ssgdotcom.option.application;

import com.ssgsakk.ssgdotcom.option.dto.AddOptionDto;
import com.ssgsakk.ssgdotcom.option.dto.OptionDto;
import com.ssgsakk.ssgdotcom.option.dto.StockDto;

import java.util.List;

public interface OptionAndStockService {
    OptionDto findOptionsByProductId(Long productId);
    //StockDto getStocks(Long productSeq, StockDto stockDto);
    void addOptions(AddOptionDto addOptionDto);
    void deleteOption(Long optionId);

    List<Integer> getStocks(Long productId, StockDto build);
}
