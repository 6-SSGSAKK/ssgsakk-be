package com.ssgsakk.ssgdotcom.point.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pointSeq;

    @Column(nullable = false, unique = true)
    private String uuid;

    @Column(nullable = false)
    private Integer point;


    @Builder
    public Point(Long pointSeq, String uuid, Integer point) {
        this.pointSeq = pointSeq;
        this.uuid = uuid;
        this.point = point;
    }
}
