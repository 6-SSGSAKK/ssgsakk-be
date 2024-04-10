package com.ssgsakk.ssgdotcom.purchase.infrastructure;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssgsakk.ssgdotcom.purchase.domain.QPurchase;
import com.ssgsakk.ssgdotcom.purchaseproduct.domain.PurchaseProduct;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PurchaseRepositoryImp extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;
    private final QPurchase qPurchase = QPurchase.purchase;

    public PurchaseRepositoryImp(JPAQueryFactory jpaQueryFactory) {
        super(PurchaseProduct.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }



    public List<Tuple> checkMemberPurchase(String uuid) { //회원이 주문한 주문번호 List 조회,uuid 를 기준으로 조회함 프로젝션 여러개기 떄문에 Tuple 로 리턴
        return jpaQueryFactory.select(qPurchase.purchaseSeq,qPurchase.purchaseuuid)
                .from(qPurchase)
                .where(qPurchase.purchaseuuid.eq(uuid)) //인자값으로 받은 uuid랑 비교
                .fetch();
    }


}
