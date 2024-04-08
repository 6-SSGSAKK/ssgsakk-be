package com.ssgsakk.ssgdotcom.contents.infrastructure;

import com.ssgsakk.ssgdotcom.contents.domain.EventContents;
import com.ssgsakk.ssgdotcom.contents.domain.ProductContents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventContentsRepository extends JpaRepository<EventContents, Long> {
    EventContents findByEvent_EventSeq(Long eventSeq);
}
