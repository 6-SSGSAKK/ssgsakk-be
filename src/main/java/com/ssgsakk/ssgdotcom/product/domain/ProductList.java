package com.ssgsakk.ssgdotcom.product.domain;

import com.ssgsakk.ssgdotcom.category.domain.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class ProductList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productListSeq;

    @ManyToOne
    @JoinColumn(name = "category_seq")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "product_seq")
    private Product product;

}
