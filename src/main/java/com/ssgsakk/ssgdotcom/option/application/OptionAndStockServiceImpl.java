package com.ssgsakk.ssgdotcom.option.application;

import com.ssgsakk.ssgdotcom.common.exception.BusinessException;
import com.ssgsakk.ssgdotcom.common.exception.ErrorCode;
import com.ssgsakk.ssgdotcom.option.domain.*;
import com.ssgsakk.ssgdotcom.option.dto.OptionDto;
import com.ssgsakk.ssgdotcom.option.dto.StockDto;
import com.ssgsakk.ssgdotcom.option.infrastructure.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class OptionAndStockServiceImpl implements OptionAndStockService {
    private final OptionAndStockRepository optionAndStockRepository;

    // 옵션이 몇개있는지 출력
    @Override
    public OptionDto getOptionList(Long productSeq) {
        List<OptionAndStock> options = optionAndStockRepository.findByProductSeq(productSeq);
        if (options.isEmpty()) {
            throw new BusinessException(ErrorCode.CANNOT_FOUND_OPTION);
        }
        OptionDto.OptionDtoBuilder builder = OptionDto.builder();

        List<String> depthNames = new ArrayList<>();

        for (OptionAndStock option : options) {
            if (option.getColor() != null) {
                depthNames.add("컬러");
            }
            if (option.getSize() != null) {
                depthNames.add("사이즈");
            }
            if (option.getCustomizationOption() != null) {
                depthNames.add(option.getCustomizationOption().getCustomizationType());
            }
            break;
        }

        int depthLevel = depthNames.size();
        List<StockDto> stocks = getStocks(productSeq, depthLevel);
        return switch (depthLevel) {
            case 1 -> builder.depthLevel(1).firstDepthName(depthNames.get(0))
                    .options(stocks).build();
            case 2 -> builder.depthLevel(2).firstDepthName(depthNames.get(0))
                    .secondDepthName(depthNames.get(1)).options(stocks).build();
            case 3 -> builder.depthLevel(3).firstDepthName(depthNames.get(0))
                    .secondDepthName(depthNames.get(1))
                    .thirdDepthName(depthNames.get(2))
                    .options(stocks)
                    .build();
            default -> builder.options(stocks).build();
        };
    }

    // 상품이 가진 옵션의 개수 따라서 다르게 출력
    public List<StockDto> getStocks(Long productSeq, Integer depthLevel) {
        List<OptionAndStock> options = optionAndStockRepository.findByProductSeq(productSeq);
        if (options.isEmpty()) {
            throw new BusinessException(ErrorCode.CANNOT_FOUND_OPTION);
        }
        return options.stream()
                .map(option -> {
                    StockDto.StockDtoBuilder builder = StockDto.builder();


                    String explain = null;
                    String explain2 = null;
                    String explain3 = null;

                    switch (depthLevel) {
                        case 1:
                            explain = option.getColor() != null ? option.getColor().getColorData() :
                                    option.getSize() != null ? option.getSize().getSizeData() :
                                            option.getCustomizationOption().getCustomizationData();
                            break;
                        case 2:
                            explain = option.getColor() != null ? option.getColor().getColorData() :
                                    option.getSize().getSizeData();
                            explain2 = option.getCustomizationOption() != null ?
                                    option.getCustomizationOption().getCustomizationData() :
                                    option.getSize().getSizeData();
                            break;
                        case 3:
                            explain = option.getColor().getColorData();
                            explain2 = option.getSize().getSizeData();
                            explain3 = option.getCustomizationOption().getCustomizationData();
                            break;
                        default:
                            break;
                    }

                    builder.OptionAndStockSeq(option.getOptionAndStockSeq())
                            .explain(explain)
                            .explain2(explain2)
                            .explain3(explain3)
                            .stock(option.getStock());


                    return builder.build();
                })
                .collect(Collectors.toList());
    }

}




