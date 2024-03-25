package com.ssgsakk.ssgdotcom.option.domain;

import com.ssgsakk.ssgdotcom.common.entity.BaseCreateTimeEntity;
import com.ssgsakk.ssgdotcom.product.domain.Product;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Getter
public class OptionAndStock extends BaseCreateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionAndStockSeq;

    @Column(nullable = false)
    private Long productSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "size_seq")
    private Size size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_seq")
    private Color color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customization_seq")
    private CustomizationOption customizationOption;

    @Column(nullable = false, length = 7)
    private Integer stock;

    @Column(nullable = false, length = 7)
    private Integer minimumStock;

    @Builder
    public OptionAndStock(Long productSeq, Size size, Color color, CustomizationOption customizationOption,
                          Integer stock, Integer minimumStock) {
        this.productSeq = productSeq;
        this.size = size;
        this.color = color;
        this.customizationOption = customizationOption;
        this.stock = stock;
        this.minimumStock = minimumStock;
    }

}
