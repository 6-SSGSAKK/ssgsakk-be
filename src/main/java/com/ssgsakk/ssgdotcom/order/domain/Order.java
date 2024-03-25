//package com.ssgsakk.ssgdotcom.order.domain;
//
//
//import jakarta.persistence.*;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.ColumnDefault;
//import org.hibernate.annotations.DynamicInsert;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//@Getter
//@DynamicInsert
//@Entity
//@NoArgsConstructor
//@EntityListeners(AuditingEntityListener.class)
//public class Order {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long orderSeq;
//
//    @Column(nullable = false)
//    private Long orderUserId;
//
//    @Column(nullable = false)
//    private String orderUserName;
//
//    @Column(nullable = false)
//    private String orderUserPhoneNumber;
//
//    @Column(nullable = false)
//    private String orderUserEmail;
//
//    @Column(nullable = false)
//    private String recipientName;
//
//    @ColumnDefault("0")
//    private String recipientPhoneNumber;
//
//    @ColumnDefault("0")
//    private String recipientEmail;
//
//    @ColumnDefault("0")
//    private Integer finalDeiveryAddressNumber;
//
//    @ColumnDefault("0")
//    private String finalDeiveryRoadAddress;
//
//    @ColumnDefault("0")
//    private String finalDeiveryAddress;
//
//    @ColumnDefault("0")
//    private String finalDeiveryDetailAddress;
//
//    @ColumnDefault("0")
//    private String DeliveryMessage;
//
//}
