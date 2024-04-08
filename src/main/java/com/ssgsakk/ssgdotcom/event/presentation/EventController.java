package com.ssgsakk.ssgdotcom.event.presentation;

import com.ssgsakk.ssgdotcom.common.response.BaseResponse;
import com.ssgsakk.ssgdotcom.common.util.DeliveryType;
import com.ssgsakk.ssgdotcom.event.vo.SearchEventResponseVo;
import com.ssgsakk.ssgdotcom.event.application.EventService;
import com.ssgsakk.ssgdotcom.event.dto.EventDto;
import com.ssgsakk.ssgdotcom.event.dto.SearchEventDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/events")
public class EventController {
    private final EventService eventService;

    @GetMapping("")
    @Operation(summary = "이벤트 목록", description = "이벤트 목록", tags = { "Event" })
    public BaseResponse<List<SearchEventResponseVo>> getEvents(
                                     @RequestParam(value = "deliveryType", required = false) DeliveryType deliveryType,
                                     @RequestParam(value = "categorySeq", required = false) Long categorySeq){
        List<SearchEventDto> searchEventDtoList = eventService.getEvents(EventDto.toDto(deliveryType, categorySeq));
        return new BaseResponse<>("Event Get Success",SearchEventResponseVo.DtoListToVoList(searchEventDtoList));
    }

}
