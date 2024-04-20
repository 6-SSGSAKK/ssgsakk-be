package com.ssgsakk.ssgdotcom.contents.presentation;

import com.ssgsakk.ssgdotcom.common.response.BaseResponse;
import com.ssgsakk.ssgdotcom.contents.application.ContentsService;
import com.ssgsakk.ssgdotcom.contents.domain.ProductContents;
import com.ssgsakk.ssgdotcom.contents.vo.ProductContentsVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/contents")
public class ContentsController {
    private ContentsService contentsService;
    // 특정 ID의 컨텐츠 조회
    @GetMapping("/{productseq}")
    public BaseResponse<?> getContentById(@PathVariable("productseq") Long productSeq) {
        List<ProductContents> contents = contentsService.productContentsList(productSeq);
        if (contents != null) {
            List<ProductContentsVo> contentVos = new ArrayList<>();
            for (ProductContents content : contents) {
                ProductContentsVo contentVo = ProductContentsVo.builder()
                        .priority(content.getPriority())
                        .contentUrl(content.getContents() != null ? content.getContents().getContentUrl() : null)
                        .contentDescription(content.getContents().getContentDescription())
                        .build();
                contentVos.add(contentVo);
            }
            return new BaseResponse<>("컨텐츠를 가져오는 데 성공했습니다.", contentVos);
        } else {
            return new BaseResponse<>("No content found for the given productseq", null);
        }
    }

}
