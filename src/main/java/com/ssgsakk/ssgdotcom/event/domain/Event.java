package com.ssgsakk.ssgdotcom.event.domain;

import com.ssgsakk.ssgdotcom.category.domain.Category;
import com.ssgsakk.ssgdotcom.common.util.DeliveryType;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_seq")
    private Category category;

    @Column(length =  10)
    private DeliveryType deliveryType;
}
