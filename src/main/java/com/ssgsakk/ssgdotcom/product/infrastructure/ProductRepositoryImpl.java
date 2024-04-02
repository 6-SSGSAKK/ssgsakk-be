package com.ssgsakk.ssgdotcom.product.infrastructure;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssgsakk.ssgdotcom.common.util.DeliveryType;
import com.ssgsakk.ssgdotcom.product.domain.QProduct;
import com.ssgsakk.ssgdotcom.product.domain.QProductList;
import com.ssgsakk.ssgdotcom.product.dto.ProductFilterDto;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;
    private final QProduct qProduct = QProduct.product;
    private final QProductList qProductList = QProductList.productList;

    public ProductRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(ProductRepositoryImpl.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<Long> productBest(DeliveryType deliveryType){
        return jpaQueryFactory
                .select(qProduct.productSeq)
                .from(qProduct)
                .where(eqDeliveryType(deliveryType))
                .orderBy(qProduct.reviewCount.desc())
                .fetch();
    }

    public List<Long> productFilter(ProductFilterDto productFilterDto) {
        return jpaQueryFactory
                .select(qProductList.product.productSeq)
                .from(qProductList)
                .where(eqDeliveryType(productFilterDto.getDeliveryType())
                                ,eqPriceRange(productFilterDto.getMinPrice()
                                ,productFilterDto.getMaxPrice())
                                ,eqCategory(productFilterDto.getCategorySeq())
                ,eqKeywordSearch(productFilterDto.getKeyword()))
                .distinct()
                .fetch();
    }

    private BooleanExpression eqDeliveryType(DeliveryType deliveryType) {
        if(deliveryType == null) {
            return null;
        }
        return qProductList.product.deliveryType.eq(deliveryType);
    }

    private BooleanExpression eqCategory(Long categorySeq){
        if (categorySeq == null) {
            return null;
        }
        return qProductList.category.categorySeq.eq(categorySeq);
    }

    private BooleanExpression eqKeywordSearch(String keyword){
        if (keyword == null || keyword.isEmpty()) {
            return null;
        }
        return qProductList.product.productName.containsIgnoreCase(keyword);
    }

    private BooleanExpression eqPriceRange(Integer minPrice, Integer maxPrice) {
        BooleanExpression minPriceExpression = minPrice != null ? qProductList.product.productPrice.goe(minPrice) : null;
        BooleanExpression maxPriceExpression = maxPrice != null ? qProductList.product.productPrice.loe(maxPrice) : null;

        if (minPriceExpression != null && maxPriceExpression != null) {
            return minPriceExpression.and(maxPriceExpression);
        } else if (minPriceExpression != null) {
            return minPriceExpression;
        } else if (maxPriceExpression != null) {
            return maxPriceExpression;
        }
        else{
            return null;
        }
    }

}
