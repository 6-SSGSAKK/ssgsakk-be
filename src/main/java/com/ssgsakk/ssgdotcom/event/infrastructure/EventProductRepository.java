package com.ssgsakk.ssgdotcom.event.infrastructure;

import com.ssgsakk.ssgdotcom.event.domain.EventProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventProductRepository extends JpaRepository<EventProduct, Long> {
    List<EventProduct> findByEvent_EventSeq(Long eventSeq);
}
