package com.ssgsakk.ssgdotcom.purchase.infrastructure;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssgsakk.ssgdotcom.purchase.domain.Purchase;
import com.ssgsakk.ssgdotcom.purchase.domain.QPurchase;
import com.ssgsakk.ssgdotcom.purchase.dto.NonMemberPurchaseRequestDto;
import com.ssgsakk.ssgdotcom.purchase.vo.CreateNonMemberPurchaseRequestVo;
import com.ssgsakk.ssgdotcom.purchase.vo.NonMemberPurchaseResponseVo;
import com.ssgsakk.ssgdotcom.purchaseproduct.domain.PurchaseProduct;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PurchaseRepositoryImp extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;
    private final QPurchase qPurchase = QPurchase.purchase;

    public PurchaseRepositoryImp(JPAQueryFactory jpaQueryFactory) {
        super(PurchaseProduct.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }



    @Transactional
    public List<Tuple> checkMemberPurchase(String uuid) { //회원이 주문한 주문번호 List 조회,uuid 를 기준으로 조회함 프로젝션 여러개기 떄문에 Tuple 로 리턴
        return jpaQueryFactory.select(qPurchase.purchaseSeq,qPurchase.purchaseuuid)
                .from(qPurchase)
                .where(qPurchase.purchaseuuid.eq(uuid)) //인자값으로 받은 uuid랑 비교
                .fetch();
    }

    @Transactional //비회원 주문번호 조회 메소드
    public List<NonMemberPurchaseResponseVo> checkNonMemberPurchase(String purchaseCode){
        //Long값으로 우선 쿼리 결과를 담는다.
        List<Long> purchaseSeqList = jpaQueryFactory.select(qPurchase.purchaseSeq)
                .from(qPurchase)
                .where(qPurchase.purchaseCode.eq(purchaseCode)) //받은 주문번호랑 비교
                .fetch();
        // 각 purchaseSeq를 NonMemberPurchaseResponseVo로 변환하여 리스트에 추가
        List<NonMemberPurchaseResponseVo> result = new ArrayList<>();
        for (Long purchaseSeq : purchaseSeqList) {
            result.add(new NonMemberPurchaseResponseVo(purchaseSeq));
        }

        return result;
    }

}
