package com.ssgsakk.ssgdotcom.contents.domain;

import com.ssgsakk.ssgdotcom.event.domain.Event;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventContents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventContentsSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_seq")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contents_seq")
    private Contents contents;

    @Builder
    public EventContents(Event event, Contents contents){
        this.event = event;
        this.contents = contents;
    }
}
