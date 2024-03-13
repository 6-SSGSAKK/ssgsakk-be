package com.ssgsakk.ssgdotcom.product.domain;

import com.ssgsakk.ssgdotcom.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@DynamicInsert
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productSeq;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private Integer productPrice;

    @Column(nullable = false)
    private String vendor;

    @Column(nullable = false)
    private String productCode;

    @Column(nullable = false)
    private String productDescription;

    @ColumnDefault("0")
    private Double discountPercent;

    @ColumnDefault("0")
    private Double averageRating;
    @ColumnDefault("0")
    private Integer reviewCount;

    @Builder
    public Product(String productName, Integer productPrice, String vendor,
                   String productCode, String productDescription, Double discountPercent,
                   Double averageRating, Integer reviewCount) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.vendor = vendor;
        this.productCode = productCode;
        this.productDescription = productDescription;
        this.discountPercent = discountPercent;
        this.averageRating = averageRating;
        this.reviewCount = reviewCount;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setDiscountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
    }

}