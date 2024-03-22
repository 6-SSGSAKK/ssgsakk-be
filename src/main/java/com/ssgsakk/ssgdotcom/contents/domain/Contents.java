package com.ssgsakk.ssgdotcom.contents.domain;

import com.ssgsakk.ssgdotcom.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Contents extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contentSeq;

    @Column(nullable = false)
    private String contentUrl;

    @Column(nullable = false)
    private String contentDescription;

}