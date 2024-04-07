package com.ssgsakk.ssgdotcom.likes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ContentsUrl {
    private int priority;
    private String contentUrl;
    private String contentDescription;

    @Builder
    public ContentsUrl(int priority, String contentUrl, String contentDescription) {
        this.priority = priority;
        this.contentUrl = contentUrl;
        this.contentDescription = contentDescription;
    }
}
