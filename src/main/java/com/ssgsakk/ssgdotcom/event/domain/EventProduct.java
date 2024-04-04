package com.ssgsakk.ssgdotcom.event.domain;

import com.ssgsakk.ssgdotcom.product.domain.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class EventProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long EventProductSeq;

    @ManyToOne
    @JoinColumn(name = "event_seq")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "product_seq")
    private Product product;
}
