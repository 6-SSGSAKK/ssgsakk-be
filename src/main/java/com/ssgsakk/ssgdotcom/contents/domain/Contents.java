package com.ssgsakk.ssgdotcom.contents.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
public class Contents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contentSeq;

    @Column(nullable = false)
    private String contentUrl;

    @Column()
    private String contentDescription;

    @Builder
    public Contents(Long contentSeq, String contentUrl, String contentDescription)
    {
        this.contentSeq = contentSeq;
        this.contentUrl = contentUrl;
        this.contentDescription = contentDescription;
    }

}