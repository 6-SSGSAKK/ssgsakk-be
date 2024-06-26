package com.ssgsakk.ssgdotcom.event.vo;

import com.ssgsakk.ssgdotcom.event.dto.SearchEventDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class SearchEventResponseVo {
    private Long eventSeq;
    private String eventName;
    private LocalDateTime eventEndDate;
    private Integer eventLowestPrice;
    private String eventThumbnail;
    private String eventVendor;
    public static List<SearchEventResponseVo> DtoListToVoList(List<SearchEventDto> searchEventDtoList) {
        return searchEventDtoList.stream()
                .map(searchEventDto -> SearchEventResponseVo.builder()
                        .eventSeq(searchEventDto.getEventSeq())
                        .eventName(searchEventDto.getEventName())
                        .eventEndDate(searchEventDto.getEventEndDate())
                        .eventThumbnail(searchEventDto.getEventThumbnail())
                        .eventVendor(searchEventDto.getEventVendor())
                        .eventLowestPrice(searchEventDto.getEventLowestPrice())
                        .build()).toList();
    }
}
