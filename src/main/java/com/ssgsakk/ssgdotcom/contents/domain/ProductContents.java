package com.ssgsakk.ssgdotcom.contents.domain;

import com.ssgsakk.ssgdotcom.common.entity.BaseTimeEntity;
import com.ssgsakk.ssgdotcom.product.domain.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductContents extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productContentsSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_seq")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contents_seq")
    private Contents contents;

    private String productContentsType;

    private String productContentsIdx;

}
