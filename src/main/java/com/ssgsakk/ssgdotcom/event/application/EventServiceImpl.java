package com.ssgsakk.ssgdotcom.event.application;

import com.ssgsakk.ssgdotcom.event.dto.EventDto;
import com.ssgsakk.ssgdotcom.event.dto.SearchEventDto;
import com.ssgsakk.ssgdotcom.event.infrastructure.EventRepositoryImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService{
    private final EventRepositoryImpl eventRepositoryImpl;
    @Override
    @Transactional
    public List<SearchEventDto> getEvents(EventDto eventDto) {
        return eventRepositoryImpl.getEvent(eventDto).stream()
                .map(event -> SearchEventDto.builder()
                        .eventSeq(event)
                        .build()).toList();
    }
}
