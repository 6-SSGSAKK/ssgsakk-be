package com.ssgsakk.ssgdotcom.contents.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductContentsVo {
    private String productContentsType;
    private String productContentsIdx;
    private String contentUrl;
    private String contentDescription;
}
