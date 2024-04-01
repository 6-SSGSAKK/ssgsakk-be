package com.ssgsakk.ssgdotcom.product.infrastructure;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssgsakk.ssgdotcom.common.util.DeliveryType;
import com.ssgsakk.ssgdotcom.product.domain.QProduct;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;
    private final QProduct qProduct = QProduct.product;

    public ProductRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(ProductRepositoryImpl.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<Long> productbest(DeliveryType deliveryType){
        return jpaQueryFactory
                .select(qProduct.productSeq)
                .from(qProduct)
                .where(eqDeliveryType(deliveryType))
                .orderBy(qProduct.reviewCount.desc())
                .fetch();
    }


    public List<Long> findByDeliveryType(DeliveryType deliveryType, Integer minPrice, Integer maxPrice) {
        return jpaQueryFactory
                .select(qProduct.productSeq)
                .from(qProduct)
                .where(eqDeliveryType(deliveryType),eqPriceRange(minPrice, maxPrice))
                .fetch();
    }
    private BooleanExpression eqDeliveryType(DeliveryType deliveryType) {
        if(deliveryType == null) {
            return null;
        }
        return qProduct.deliveryType.eq(deliveryType);
    }

    private BooleanExpression eqPriceRange(Integer minPrice, Integer maxPrice) {
        BooleanExpression minPriceExpression = minPrice != null ? qProduct.productPrice.goe(minPrice) : null;
        BooleanExpression maxPriceExpression = maxPrice != null ? qProduct.productPrice.loe(maxPrice) : null;

        if (minPriceExpression != null && maxPriceExpression != null) {
            return minPriceExpression.and(maxPriceExpression);
        } else if (minPriceExpression != null) {
            return minPriceExpression;
        } else if (maxPriceExpression != null) {
            return maxPriceExpression;
        } else {
            return null;
        }
    }

}
