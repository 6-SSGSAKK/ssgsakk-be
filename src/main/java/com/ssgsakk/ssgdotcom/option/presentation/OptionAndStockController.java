package com.ssgsakk.ssgdotcom.option.presentation;

import com.ssgsakk.ssgdotcom.common.response.BaseResponse;
import com.ssgsakk.ssgdotcom.option.application.OptionAndStockService;
import com.ssgsakk.ssgdotcom.option.dto.OptionDto;
import com.ssgsakk.ssgdotcom.option.vo.OptionVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/optionstock")
public class OptionAndStockController {
    private final OptionAndStockService optionAndStockService;

    // 옵션 정보 가져오기
    @GetMapping("/{productId}")
    public BaseResponse<?> getOptionList(@PathVariable("productId") Long productId) {
        OptionDto optionDto = optionAndStockService.getOptionList(productId);

        return new BaseResponse<>("Get Options Success", OptionVo.builder()
                .depthLevel(optionDto.getDepthLevel())
                .firstDepthName(optionDto.getFirstDepthName())
                .secondDepthName(optionDto.getSecondDepthName())
                .thirdDepthName(optionDto.getThirdDepthName())
                .options(optionDto.getOptions())
                .build());
    }
}
