package com.ssgsakk.ssgdotcom.contents.domain;

import com.ssgsakk.ssgdotcom.product.domain.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductContents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productContentsSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_seq")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contents_seq")
    private Contents contents;

    private Integer priority;

    @Builder
    public ProductContents(Product product, Contents contents, Integer priority){
        this.product = product;
        this.contents = contents;
        this.priority = priority;
    }

}
