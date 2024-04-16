package com.ssgsakk.ssgdotcom.payment.domain;
//todo
// 추후 개발 예정

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@Entity
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentSeq;

    @Column(nullable = false)
    private Long pointUserId;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private Integer finalPoint;
}
