package com.ssgsakk.ssgdotcom.product.domain;

import com.ssgsakk.ssgdotcom.common.utils.DeliveryType;
import com.ssgsakk.ssgdotcom.vendor.domain.Vendor;
import jakarta.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productSeq;

    @Column(nullable = false, length = 50)
    private String productName;

    @Column(nullable = false, length = 15)
    private Integer productPrice;

    @ManyToOne
    @JoinColumn(name = "vendor_seq")
    private Vendor vendor;

    @Column(length = 200)
    private String productDescription;

    @Column(nullable = false, length = 3)
    private Integer discountPercent;

    @Column(nullable = false, length = 15)
    private Double averageRating;

    @Column(nullable = false, length = 7)
    private Integer reviewCount;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private DeliveryType deliveryType;


    @Builder
    public Product(Long productSeq, String productName, Integer productPrice, Vendor vendor,
                   String productDescription, Integer discountPercent,
                   Double averageRating, Integer reviewCount, DeliveryType deliveryType) {
        this.productSeq = productSeq;
        this.productName = productName;
        this.productPrice = productPrice;
        this.vendor = vendor;
        this.productDescription = productDescription;
        this.discountPercent = discountPercent;
        this.averageRating = averageRating;
        this.reviewCount = reviewCount;
        this.deliveryType = deliveryType;
    }

}