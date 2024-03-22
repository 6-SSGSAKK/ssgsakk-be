package com.ssgsakk.ssgdotcom.option.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Getter
public class CustomizationOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customizationOptionSeq;

    @Column(nullable = false)
    private String customizationType;

    @Column(nullable = false)
    private String customizationData;
}
