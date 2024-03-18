package com.ssgsakk.ssgdotcom.payment.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@DynamicInsert
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentSeq;

    @Column(nullable = false)
    private Long pointUserId;

    @Column(nullable = false, columnDefinition = "0")
    private Integer finalPoint;
}
