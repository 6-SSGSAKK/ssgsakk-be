package com.ssgsakk.ssgdotcom.purchaseproduct.infrastructure;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssgsakk.ssgdotcom.option.domain.OptionAndStock;
import com.ssgsakk.ssgdotcom.purchaseproduct.domain.PurchaseProduct;
import com.ssgsakk.ssgdotcom.purchaseproduct.domain.QPurchaseProduct;
import com.ssgsakk.ssgdotcom.purchaseproduct.dto.PurchaseProductStateDto;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import static com.ssgsakk.ssgdotcom.option.domain.QOptionAndStock.optionAndStock;

@Repository
public class PurchaseProductRepositoryImp extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;
    private final QPurchaseProduct qPurchaseProduct = QPurchaseProduct.purchaseProduct;


    public PurchaseProductRepositoryImp(JPAQueryFactory jpaQueryFactory) {
        super(PurchaseProduct.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }


    @Transactional //주문시 상품ID 별 수량에 따라 재고를 감소시키는 메소드
    public void decreaseProductStock(Long productSeq, Integer purchaseProductCount) { //상품ID,수량을 받음
        Long decreaseStock = jpaQueryFactory.update(optionAndStock)
                .set(optionAndStock.stock, optionAndStock.stock.subtract(purchaseProductCount)) //Stock 을 상품수량만큼 감소시킴.
                .where(optionAndStock.productSeq.eq(productSeq))
                .execute();
        if (decreaseStock == 0){ //메소드 실행결과가 0이면 예외를 발생시킴.
            throw new RuntimeException("reduce Stock fail cause stock < 1");
        }

    }

    @Transactional
    public void updateProductState(Long purchaseProductSeq, PurchaseProductStateDto purchaseProductStateDto)  {

        long updateProductState = jpaQueryFactory.update(qPurchaseProduct)
                .set(qPurchaseProduct.productState, purchaseProductStateDto.getPurchaseProductState())
                .where(qPurchaseProduct.purchaseProductSeq.eq(purchaseProductSeq))
                .execute();

        if (updateProductState > 8){
            throw new RuntimeException("존재하지 않는 상태번호");

        } else if (updateProductState == 5){
            increaseProductStock(purchaseProductSeq);
        }
    }

    public void increaseProductStock(Long purchaseProductSeq){


    }


}
