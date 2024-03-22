package com.ssgsakk.ssgdotcom.option.presentation;

import com.ssgsakk.ssgdotcom.common.response.BaseResponse;
import com.ssgsakk.ssgdotcom.option.application.OptionAndStockService;
import com.ssgsakk.ssgdotcom.option.dto.AddOptionDto;
import com.ssgsakk.ssgdotcom.option.dto.OptionAndStockDto;
import com.ssgsakk.ssgdotcom.option.vo.AddOptionVo;
import com.ssgsakk.ssgdotcom.option.vo.OptionAndStockVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/option")
public class OptionAndStockController {
    private final OptionAndStockService optionAndStockService;

    @GetMapping("/{productId}")
    public BaseResponse<?> getOptions(@PathVariable("productId") Long productId) {
        List<OptionAndStockDto> optionAndStockDtoList = optionAndStockService.findOptionsByProductId(productId);

        return new BaseResponse<>("Get Options Success", optionAndStockDtoList.stream()
                .map(optionAndStockDto -> OptionAndStockVo.builder()
                        .optionAndStockSeq(optionAndStockDto.getOptionAndStockSeq())
                        .size(optionAndStockDto.getSize())
                        .color(optionAndStockDto.getColor())
                        .customizationOption(optionAndStockDto.getCustomizationOption())
                        .stock(optionAndStockDto.getStock())
                        .minimumStock(optionAndStockDto.getMinimumStock())
                        .build())
                .collect(Collectors.toList()));
    }

    @PutMapping("/add")
    public BaseResponse<?> addOptions(@RequestBody AddOptionVo addOptionVo) {
        AddOptionDto addOptionDto = AddOptionDto.builder()
                .productSeq(addOptionVo.getProductSeq())
                .sizeSeq(addOptionVo.getSizeSeq())
                .colorSeq(addOptionVo.getColorSeq())
                .customizationOptionSeq(addOptionVo.getCustomizationOptionSeq())
                .stock(addOptionVo.getStock())
                .minimumStock(addOptionVo.getMinimumStock())
                .build();

        optionAndStockService.addOptions(addOptionDto);

        return new BaseResponse<>("addProduct Success", "");
    }

    @DeleteMapping("/{optionId}")
    public BaseResponse<?> deleteOption(@PathVariable Long optionId) {
        optionAndStockService.deleteOption(optionId);
        return new BaseResponse<>("Option deleted successfully", "");
    }

}
