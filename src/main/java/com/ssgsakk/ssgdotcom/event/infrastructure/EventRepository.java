package com.ssgsakk.ssgdotcom.event.infrastructure;

import com.ssgsakk.ssgdotcom.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
