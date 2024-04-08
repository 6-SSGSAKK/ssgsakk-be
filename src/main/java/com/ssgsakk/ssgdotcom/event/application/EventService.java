package com.ssgsakk.ssgdotcom.event.application;

import com.ssgsakk.ssgdotcom.event.dto.EventDto;
import com.ssgsakk.ssgdotcom.event.dto.SearchEventDto;

import java.util.List;

public interface EventService {
    List<SearchEventDto> getEvents(EventDto eventDto);
}
