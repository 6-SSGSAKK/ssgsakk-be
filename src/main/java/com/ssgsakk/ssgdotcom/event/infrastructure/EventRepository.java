package com.ssgsakk.ssgdotcom.event.infrastructure;

import com.ssgsakk.ssgdotcom.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT MIN(ep.product.productPrice) FROM EventProduct ep WHERE ep.event = :event")
    Integer findMinPriceByEvent(@Param("event") Event event);

    @Query("SELECT ep.product.vendor.vendorName FROM EventProduct ep WHERE ep.event = :event")
    String findEventVendor(@Param("event") Event event);

}
