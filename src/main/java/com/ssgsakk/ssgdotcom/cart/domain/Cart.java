package com.ssgsakk.ssgdotcom.cart.domain;

import com.ssgsakk.ssgdotcom.common.entity.BaseTimeEntity;
import com.ssgsakk.ssgdotcom.member.domain.User;
import com.ssgsakk.ssgdotcom.option.domain.OptionAndStock;
import com.ssgsakk.ssgdotcom.product.domain.Product;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Cart extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_and_stock_seq")
    private OptionAndStock optionAndStock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_seq")
    private Product product;

    @Column(nullable = false, length = 3)
    private Integer quantity;

    @Column(columnDefinition = "integer default 0" ,length = 1)
    private Integer checkbox;

    @Column(columnDefinition = "integer default 0" , length = 1)
    private Integer fixItem;

    @Builder
    public Cart(Long cartSeq, User user, OptionAndStock optionAndStock, Product product,
                Integer quantity, Integer checkbox, Integer fixItem){
        this.user = user;
        this.cartSeq = cartSeq;
        this.optionAndStock = optionAndStock;
        this.product = product;
        this.quantity = quantity;
        this.checkbox = checkbox;
        this.fixItem = fixItem;
    }
}

