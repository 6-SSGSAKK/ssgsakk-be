package com.ssgsakk.ssgdotcom.event.application;

import com.ssgsakk.ssgdotcom.contents.infrastructure.EventContentsRepository;
import com.ssgsakk.ssgdotcom.event.domain.Event;
import com.ssgsakk.ssgdotcom.event.dto.EventDto;
import com.ssgsakk.ssgdotcom.event.dto.SearchEventDto;
import com.ssgsakk.ssgdotcom.event.infrastructure.EventRepository;
import com.ssgsakk.ssgdotcom.event.infrastructure.EventRepositoryImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService{
    private final EventRepositoryImpl eventRepositoryImpl;
    private final EventRepository eventRepository;
    private final EventContentsRepository eventContentsRepository;
    @Override
    @Transactional
    public List<SearchEventDto> getEvents(EventDto eventDto) {
        return eventRepositoryImpl.getEvent(eventDto).stream()
                .map(event -> SearchEventDto.builder()
                        .eventSeq(event.getEventSeq())
                        .eventName(event.getEventName())
                        .eventEndDate(event.getEventEndDate())
                        .eventLowestPrice(eventRepository.findMinPriceByEvent(event))
                        .eventVendor(eventRepository.findEventVendor(event))
                        .eventThumbnail(eventContentsRepository.findByEvent_EventSeq(event.getEventSeq())
                                .getContents().getContentUrl())
                        .build()).toList();
    }
}
