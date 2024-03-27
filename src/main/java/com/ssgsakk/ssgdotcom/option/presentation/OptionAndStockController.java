package com.ssgsakk.ssgdotcom.option.presentation;

import com.ssgsakk.ssgdotcom.common.response.BaseResponse;
import com.ssgsakk.ssgdotcom.option.application.OptionAndStockService;
import com.ssgsakk.ssgdotcom.option.dto.AddOptionDto;
import com.ssgsakk.ssgdotcom.option.dto.OptionDto;
import com.ssgsakk.ssgdotcom.option.dto.StockDto;
import com.ssgsakk.ssgdotcom.option.vo.AddOptionVo;
import com.ssgsakk.ssgdotcom.option.vo.OptionAndStockVo;
import com.ssgsakk.ssgdotcom.option.vo.StockRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/option")
public class OptionAndStockController {
    private final OptionAndStockService optionAndStockService;

    @GetMapping("/{productId}")
    public BaseResponse<?> getOptions(@PathVariable("productId") Long productId) {
        OptionDto optionDto = optionAndStockService.findOptionsByProductId(productId);

        return new BaseResponse<>("Get Options Success", OptionAndStockVo.builder()
                        .productSeq(productId)
                        .size(optionDto.getSize())
                        .color(optionDto.getColor())
                        .customizationOption(optionDto.getCustomizationOption())
                        .build());
    }

    @PostMapping("/stock/{productId}")
    public BaseResponse<?> getStocks(@PathVariable("productId") Long productId,
                                     @RequestBody StockRequestVo stockRequestVo){
        List<Integer> responseStockDto = optionAndStockService.getStocks(productId,
                StockDto.builder()
                        .colorSeq(stockRequestVo.getColorSeq())
                        .sizeSeq(stockRequestVo.getSizeSeq())
                        .customizationOptionSeq(stockRequestVo.getCustomizationOptionSeq())
                        .build());
        return new BaseResponse<>("", responseStockDto);
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
