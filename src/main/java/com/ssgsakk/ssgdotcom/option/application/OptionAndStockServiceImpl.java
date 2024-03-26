package com.ssgsakk.ssgdotcom.option.application;

import com.ssgsakk.ssgdotcom.option.domain.*;
import com.ssgsakk.ssgdotcom.option.dto.AddOptionDto;
import com.ssgsakk.ssgdotcom.option.dto.OptionDto;
import com.ssgsakk.ssgdotcom.option.dto.StockDto;
import com.ssgsakk.ssgdotcom.option.infrastructure.*;

import com.ssgsakk.ssgdotcom.product.dto.SearchProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.AbstractMap;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class OptionAndStockServiceImpl implements OptionAndStockService {
    private final OptionAndStockRepository optionAndStockRepository;
    private final SizeRepository sizeRepository;
    private final ColorRepository colorRepository;
    private final CustomizationRepository customizationRepository;
    private final OptionAndStockImpl optionAndStockImpl;


    @Override
    public OptionDto findOptionsByProductId(Long productId) {
        List<OptionAndStock> options = optionAndStockRepository.findByProductSeq(productId);

        List<AbstractMap.SimpleEntry<Long, String>> colorList = options.stream()
                .map(optionAndStock -> optionAndStock.getColor() != null ?
                        new AbstractMap.SimpleEntry<>(optionAndStock.getColor().getColorSeq(),
                                optionAndStock.getColor().getColorData())
                        : null)
                .collect(Collectors.toList());

        List<AbstractMap.SimpleEntry<Long, String>> sizeList = options.stream()
                .map(optionAndStock -> optionAndStock.getSize() != null ?
                        new AbstractMap.SimpleEntry<>(optionAndStock.getSize().getSizeSeq(),
                                optionAndStock.getSize().getSizeData())
                        : null)
                .collect(Collectors.toList());

        List<AbstractMap.SimpleEntry<Long, String>> customizationList = options.stream()
                .map(optionAndStock -> optionAndStock.getCustomizationOption() != null ?
                        new AbstractMap.SimpleEntry<>(optionAndStock.getCustomizationOption().getCustomizationOptionSeq(),
                                optionAndStock.getCustomizationOption().getCustomizationData())
                        : null)
                .collect(Collectors.toList());

        return OptionDto.builder()
                .color(colorList)
                .size(sizeList)
                .customizationOption(customizationList)
                .build();
    }

    @Override
    public List<StockDto> getStocks(StockDto stockDto) {
        List<OptionAndStock> optionAndStocks =  optionAndStockImpl.getOptionInfoByProduct(
                stockDto.getProductSeq(), stockDto.getColorSeq(),
                stockDto.getSizeSeq(), stockDto.getCustomizationOptionSeq()
        );
        return optionAndStocks.stream()
                .map(optionAndStock -> StockDto.builder()
                        .optionAndStockSeq(optionAndStock.getOptionAndStockSeq())
                        .productSeq(optionAndStock.getProductSeq())
                        .stock(optionAndStock.getStock())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void addOptions(AddOptionDto addOptionDto) {

        optionAndStockRepository.save(OptionAndStock.builder()
                .productSeq(addOptionDto.getProductSeq())
                .size(sizeRepository.findById(addOptionDto.getSizeSeq()).orElse(null))
                .color(colorRepository.findById(addOptionDto.getColorSeq()).orElse(null))
                .customizationOption(customizationRepository.findById(addOptionDto.getCustomizationOptionSeq()).orElse(null))
                .stock(addOptionDto.getStock())
                .minimumStock(addOptionDto.getMinimumStock())
                .build());
    }
    @Transactional
    @Override
    public void deleteOption(Long optionId) {
        optionAndStockRepository.deleteById(optionId);
    }

}
