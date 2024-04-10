package com.ssgsakk.ssgdotcom.purchase.infrastructure;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssgsakk.ssgdotcom.purchase.domain.Purchase;
import com.ssgsakk.ssgdotcom.purchase.domain.QPurchase;
import com.ssgsakk.ssgdotcom.purchase.dto.PurchaseListDto;
import com.ssgsakk.ssgdotcom.purchaseproduct.domain.PurchaseProduct;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

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



    public List<Tuple> checkMemberPurchase(String uuid) { //회원이 주문한 주문번호 List 조회,uuid 를 기준으로 조회함 프로젝션 여러개기 떄문에 Tuple 로 리턴
        return jpaQueryFactory.select(qPurchase.purchaseSeq,qPurchase.purchaseuuid)
                .from(qPurchase)
                .where(qPurchase.purchaseuuid.eq(uuid)) //인자값으로 받은 uuid랑 비교
                .fetch();
    }

    public List<PurchaseListDto> memberPurchaseDetail(Long purchaseSeq){
        //회원 주문 상세내역조회 DTO를 List로 담아서 return 해줌
        //purchase -> PurchaseListDto를 해줘야한다.

        return jpaQueryFactory.selectFrom(qPurchase)
                .from(qPurchase)
                .where(qPurchase.purchaseSeq.eq(purchaseSeq))
                .fetch()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

    }
    //purchase -> PurchaseListDto 해주는 메소드
    private PurchaseListDto convertToDto(Purchase purchase){
        return new PurchaseListDto(purchase.getPurchaseSeq(), purchase.getPurchaser(), purchase.getPurchaseCode(),
                purchase.getPurchaseuuid(), purchase.getPurchaserPhoneNum(), purchase.getPurchaseEmail(), purchase.getRecipient(),
                purchase.getRecipientPhoneNum(),purchase.getRecipientEmail(), purchase.getPurchaseZipcode(),
                purchase.getPurchaseRoadAddress(),purchase.getPurchaseJibunAddress(), purchase.getPurchaseDetailAddress(), purchase.getDeliverymessage(),
                purchase.getShippingFee());
    }
}
