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

    @Column(nullable = false, length = 1)
    private Short checkbox;

    @Column(nullable = false, length = 1)
    private Short fix_item;

    @Builder
    public Cart(Long cartSeq, User user, OptionAndStock optionAndStock, Product product,
                Integer quantity, Short checkbox, Short fix_item){
        this.user = user;
        this.cartSeq = cartSeq;
        this.optionAndStock = optionAndStock;
        this.product = product;
        this.quantity = quantity;
        this.checkbox = checkbox;
        this.fix_item = fix_item;
    }
}

