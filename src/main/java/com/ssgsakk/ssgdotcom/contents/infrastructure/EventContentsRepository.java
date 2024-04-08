package com.ssgsakk.ssgdotcom.contents.infrastructure;

import com.ssgsakk.ssgdotcom.contents.domain.EventContents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventContentsRepository extends JpaRepository<EventContents, Long> {
}
