package com.ssgsakk.ssgdotcom.option.infrastructure;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssgsakk.ssgdotcom.option.domain.OptionAndStock;
import com.ssgsakk.ssgdotcom.option.domain.QOptionAndStock;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OptionAndStockImpl extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;
    private final QOptionAndStock qOptionAndStock = QOptionAndStock.optionAndStock;

    public OptionAndStockImpl(JPAQueryFactory jpaQueryFactory) {
        super(OptionAndStock.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<Integer> getOptionInfoByProduct(Long productSeq, Long sizeSeq,
                                                Long colorSeq, Long customizationSeq) {
        return jpaQueryFactory.select(qOptionAndStock.stock)
                .from(qOptionAndStock)
                .where(eqProduct(productSeq), eqSize(sizeSeq),
                        eqColor(colorSeq), eqCustomizationOption(customizationSeq))
                .fetch();
    }

    private BooleanExpression eqProduct(Long productSeq) {
        if(productSeq == null || productSeq == 0) {
            return null;
        }
        return qOptionAndStock.productSeq.eq(productSeq);
    }

    private BooleanExpression eqSize(Long sizeSeq) {
        if(sizeSeq == null) {
            return null;
        }
        return qOptionAndStock.size.sizeSeq.eq(sizeSeq);
    }

    private BooleanExpression eqColor(Long colorSeq) {
        if(colorSeq == null) {
            return null;
        }
        return qOptionAndStock.color.colorSeq.eq(colorSeq);
    }
    private BooleanExpression eqCustomizationOption(Long customizationOptionSeq) {
        if(customizationOptionSeq == null) {
            return null;
        }
        return qOptionAndStock.customizationOption.customizationOptionSeq.eq(customizationOptionSeq);
    }
}
