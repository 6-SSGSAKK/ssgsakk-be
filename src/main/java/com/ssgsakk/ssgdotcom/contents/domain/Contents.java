package com.ssgsakk.ssgdotcom.contents.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contentSeq;

    @Column(nullable = false)
    private String contentUrl;

    @Column(nullable = false)
    private String contentDescription;

}