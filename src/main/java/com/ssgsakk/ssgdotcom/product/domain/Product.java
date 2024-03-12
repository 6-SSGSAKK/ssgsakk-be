package com.ssgsakk.ssgdotcom.product.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productSeq;

    private String productName;
    private Integer productPrice;
    private String vendor;
    private Date createdAt;
    private String productCode;
    private String productDescription;
    private Double discountPercent;
    private Double averageRating;
    private Integer reviewCount;

    @Builder
    public Product(String productName, Integer productPrice, String vendor, Date createdAt,
                   String productCode, String productDescription, Double discountPercent,
                   Double averageRating, Integer reviewCount) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.vendor = vendor;
        this.createdAt = createdAt;
        this.productCode = productCode;
        this.productDescription = productDescription;
        this.discountPercent = discountPercent;
        this.averageRating = averageRating;
        this.reviewCount = reviewCount;
    }
}