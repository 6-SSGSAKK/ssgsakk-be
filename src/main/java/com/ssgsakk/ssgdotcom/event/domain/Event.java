package com.ssgsakk.ssgdotcom.event.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventSeq;

    @Column(length = 50)
    private String eventName;

    @Column(length =  20)
    private LocalDateTime eventStartDate;

    @Column(length =  20)
    private LocalDateTime eventEndDate;
}
