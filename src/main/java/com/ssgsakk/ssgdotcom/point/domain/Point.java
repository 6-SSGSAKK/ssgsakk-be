//package com.ssgsakk.ssgdotcom.point.domain;
//
//import jakarta.persistence.*;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.DynamicInsert;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//@Getter
//@DynamicInsert
//@Entity
//@NoArgsConstructor
//@EntityListeners(AuditingEntityListener.class)
//public class Point {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long pointSeq;
//
//    @Column(nullable = false)
//    private Long pointUserId;
//
//    @Column(nullable = false, columnDefinition = "0")
//    private Integer finalPoint;
//
//
//    @Builder
//    public Point(Long pointSeq, Long pointUserId, Integer finalPoint) {
//        this.pointSeq = pointSeq;
//        this.pointUserId = pointUserId;
//        this.finalPoint = finalPoint;
//    }
//}
