package com.ssgsakk.ssgdotcom.event.dto;

import com.ssgsakk.ssgdotcom.common.util.DeliveryType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class EventDto {
    private Long categorySeq;
    private DeliveryType deliveryType;
    public static EventDto toDto(DeliveryType deliveryType, Long categorySeq) {
        return EventDto.builder()
                .categorySeq(categorySeq)
                .deliveryType(deliveryType)
                .build();
    }
}
