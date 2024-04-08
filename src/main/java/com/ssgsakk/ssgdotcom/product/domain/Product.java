package com.ssgsakk.ssgdotcom.product.domain;

import com.ssgsakk.ssgdotcom.common.util.DeliveryType;
import com.ssgsakk.ssgdotcom.vendor.domain.Vendor;
import jakarta.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.ColumnDefault;

@Getter
@Entity
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productSeq;

    @Column(nullable = false, length = 100)
    private String productName;

    @Column(nullable = false, length = 15)
    private Integer productPrice;

    @ManyToOne
    @JoinColumn(name = "vendor_seq")
    private Vendor vendor;

    @Column(columnDefinition = "text")
    private String productDescription;

    @Column(nullable = false, length = 3)
    @ColumnDefault("0")
    private Integer discountPercent;

    @Column(nullable = false, length = 15)
    @ColumnDefault("0")
    private Double averageRating;

    @Column(nullable = false, length = 7)
    @ColumnDefault("0")
    private Integer reviewCount;

    @Enumerated(EnumType.ORDINAL)
    @Column(length = 10)
    private DeliveryType deliveryType;

    @Column(nullable = false, length = 10)
    @ColumnDefault("0")
    private Integer soldCount;


    @Builder
    public Product(Long productSeq, String productName, Integer productPrice, Vendor vendor,
                   String productDescription, Integer discountPercent, Integer soldCount,
                   Double averageRating, Integer reviewCount, DeliveryType deliveryType) {
        this.productSeq = productSeq;
        this.productName = productName;
        this.productPrice = productPrice;
        this.vendor = vendor;
        this.productDescription = productDescription;
        this.discountPercent = discountPercent;
        this.soldCount = soldCount;
        this.averageRating = averageRating;
        this.reviewCount = reviewCount;
        this.deliveryType = deliveryType;
    }

}