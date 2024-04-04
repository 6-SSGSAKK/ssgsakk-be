package com.ssgsakk.ssgdotcom.event.Vo;

import com.ssgsakk.ssgdotcom.event.dto.SearchEventDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class SearchEventResponseVo {
    private Long eventSeq;
    public static List<SearchEventResponseVo> DtoListToVoList(List<SearchEventDto> searchEventDtoList) {
        return searchEventDtoList.stream()
                .map(searchEventDto -> SearchEventResponseVo.builder()
                        .eventSeq(searchEventDto.getEventSeq())
                        .build()).toList();
    }
}
