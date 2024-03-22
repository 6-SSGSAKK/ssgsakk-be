package com.ssgsakk.ssgdotcom.contents.presentation;

import com.ssgsakk.ssgdotcom.common.response.BaseResponse;
import com.ssgsakk.ssgdotcom.contents.application.ContentsService;
import com.ssgsakk.ssgdotcom.contents.domain.ProductContents;
import com.ssgsakk.ssgdotcom.contents.vo.ProductContentsVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/contents")
public class ContentsController {
    private ContentsService contentsService;
    // 특정 ID의 컨텐츠 조회
    @GetMapping("/{productseq}")
    public BaseResponse<List<ProductContentsVo>> getContentById(@PathVariable("productseq") Long productseq) {
        List<ProductContents> contents = contentsService.productContentsList(productseq);
        if (contents != null) {
            List<ProductContentsVo> contentVos = new ArrayList<>();
            for (ProductContents content : contents) {
                ProductContentsVo contentVo = ProductContentsVo.builder()
                        .productContentsType(content.getProductContentsType())
                        .productContentsIdx(content.getProductContentsIdx())
                        .contentUrl(content.getContents() != null ? content.getContents().getContentUrl() : null)
                        .contentDescription(content.getContents().getContentDescription())
                        .build();
                contentVos.add(contentVo);
            }
            return new BaseResponse<>("Success", contentVos);
        } else {
            return new BaseResponse<>("No content found for the given productseq", null);
        }
    }
}
