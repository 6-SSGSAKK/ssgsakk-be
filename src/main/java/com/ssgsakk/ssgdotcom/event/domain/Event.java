package com.ssgsakk.ssgdotcom.event.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long event_seq;

    @Column(length = 50)
    private String eventName;

    @Column(length =  20)
    private LocalDateTime eventStartDate;

    @Column(length =  20)
    private LocalDateTime eventEndDate;
}
