package com.ssgsakk.ssgdotcom.event.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class SearchEventDto {
    private Long eventSeq;
    private String eventName;
    private LocalDateTime eventEndDate;
    private Integer eventLowestPrice;
    private String eventThumbnail;
    private String eventVendor;
}
