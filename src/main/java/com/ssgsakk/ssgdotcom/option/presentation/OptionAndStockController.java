package com.ssgsakk.ssgdotcom.option.presentation;

import com.ssgsakk.ssgdotcom.common.response.BaseResponse;
import com.ssgsakk.ssgdotcom.option.application.OptionAndStockService;
import com.ssgsakk.ssgdotcom.option.dto.AddOptionDto;
import com.ssgsakk.ssgdotcom.option.dto.OptionDto;
import com.ssgsakk.ssgdotcom.option.dto.StockDto;
import com.ssgsakk.ssgdotcom.option.vo.AddOptionVo;
import com.ssgsakk.ssgdotcom.option.vo.OptionVo;
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
    public BaseResponse<?> getOptionList(@PathVariable("productId") Long productId) {
        OptionDto optionDto = optionAndStockService.getOptionList(productId);

        return new BaseResponse<>("Get Options Success", OptionVo.builder()
                .depthLevel(optionDto.getDepthLevel())
                .firstDepthName(optionDto.getFirstDepthName())
                .secondDepthName(optionDto.getSecondDepthName())
                .thirdDepthName(optionDto.getThirdDepthName())
                .stockDto(optionDto.getStockDto())
                .build());
    }
}
