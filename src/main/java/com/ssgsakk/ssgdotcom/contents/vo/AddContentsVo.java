package com.ssgsakk.ssgdotcom.contents.vo;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
public class AddContentsVo {
    private Long productSeq;
    private String description;
    private String type;
    private Integer priority;
    private MultipartFile file;
}
