package com.ssgsakk.ssgdotcom.review.infrastructure;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssgsakk.ssgdotcom.purchase.domain.QPurchase;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepositoryImpl extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;
    private final QPurchase qPurchase = QPurchase.purchase;

    public ReviewRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(ReviewRepositoryImpl.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

//    public List<Long>(String uuid){
//        return jpaQueryFactory
//                .select()
//    }
}
