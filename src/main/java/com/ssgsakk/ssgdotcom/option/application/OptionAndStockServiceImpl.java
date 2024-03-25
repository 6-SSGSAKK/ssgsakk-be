package com.ssgsakk.ssgdotcom.option.application;

import com.ssgsakk.ssgdotcom.option.domain.*;
import com.ssgsakk.ssgdotcom.option.dto.AddOptionDto;
import com.ssgsakk.ssgdotcom.option.dto.OptionAndStockDto;
import com.ssgsakk.ssgdotcom.option.infrastructure.ColorRepository;
import com.ssgsakk.ssgdotcom.option.infrastructure.CustomizationRepository;
import com.ssgsakk.ssgdotcom.option.infrastructure.OptionAndStockRepository;
import com.ssgsakk.ssgdotcom.option.infrastructure.SizeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    @Override
    public List<OptionAndStockDto> findOptionsByProductId(Long productId) {
        return optionAndStockRepository.findByProductSeq(productId).stream()
                .map(optionAndStock -> OptionAndStockDto.builder()
                        .optionAndStockSeq(optionAndStock.getOptionAndStockSeq())
                        .stock(optionAndStock.getStock())
                        .minimumStock(optionAndStock.getMinimumStock())
                        .color(optionAndStock.getColor() != null ? optionAndStock.getColor().getColorData() : null)
                        .size(optionAndStock.getSize() != null ? optionAndStock.getSize().getSizeData() : null)
                        .customizationOption(optionAndStock.getCustomizationOption() != null
                                ? optionAndStock.getCustomizationOption() : null)
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
