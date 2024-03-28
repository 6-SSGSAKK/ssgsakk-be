package com.ssgsakk.ssgdotcom.category.infrastructure;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssgsakk.ssgdotcom.category.domain.Category;
import com.ssgsakk.ssgdotcom.category.domain.QCategory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepositoryImp extends QuerydslRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;
    private final QCategory qCategory = QCategory.category;

    public CategoryRepositoryImp(JPAQueryFactory jpaQueryFactory) {
        super(Category.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<Category> getCategoryList(){
        return jpaQueryFactory.selectFrom(qCategory).fetch(); //전체카테고리 조회
    }

//    public List<Purchase> getPurchaseByProductCategoryId(Integer categoryId, Long memberId) {
//        return jpaQueryFactory.select(qPurchase)
//                .from(qPurchase)
//                .where(eqCategory(categoryId), eqMember(memberId))
//                .fetch();
//    }
//
//    private BooleanExpression eqCategory(Integer category) {
//        if(category == null || category == 0) {
//            return null;
//        }
//        return qPurchase.product.categoryId.eq(category);
//    }
//
//    private BooleanExpression eqMember(Long memberId) {
//        if(memberId == null || memberId == 0) {
//            return null;
//        }
//        return qPurchase.member.id.eq(memberId);
//    }




}