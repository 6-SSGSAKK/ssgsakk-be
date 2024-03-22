package com.ssgsakk.ssgdotcom.cart;

import com.ssgsakk.ssgdotcom.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@DynamicInsert
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Cart extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartSeq;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Short checkbox;

    @Column(nullable = false)
    private Short fix_item;
}

