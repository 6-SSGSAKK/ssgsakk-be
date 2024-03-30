package com.ssgsakk.ssgdotcom.category.infrastructure;
import com.querydsl.core.Tuple;
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

    public List<Category> getCategoryList() { //전체카테고리 조회

        return jpaQueryFactory.selectFrom(qCategory).fetch();
    }

    public List<Tuple> getBigCategory() { //대카테고리조회
        return jpaQueryFactory
                .select(qCategory.categorySeq, qCategory.level, qCategory.categoryName)
                .from(qCategory)
                .where(qCategory.level.eq(0))
                .fetch();
    }

    public List<Tuple> getMiddleCategoryByBig(Long parentCategoryId) { //대카테고리별 중카테고리 조회
        return jpaQueryFactory
                .select(qCategory.categorySeq, qCategory.categoryName, qCategory.level)
                .from(qCategory)
                .where(qCategory.parentCategorySeq.categorySeq.eq(parentCategoryId).and(qCategory.level.eq(1)))
                .fetch();
    }




    public List<Category> getSamllCategoryByMid(Long parentCategorySeq){ //중카테고리별 소 카테고리조회
        return jpaQueryFactory.selectFrom(qCategory)
                .leftJoin(qCategory.parentCategorySeq)
                .where(qCategory.level.eq(2)
                        .and(qCategory.parentCategorySeq.categorySeq.eq(parentCategorySeq)))
                .fetch();
    }

//    public List<Tuple> getMidCategory() { //중카테고리조회
//        return jpaQueryFactory
//                .select(qCategory.categorySeq, qCategory.level, qCategory.categoryName)
//                .from(qCategory)
//                .where(qCategory.level.eq(1))
//                .fetch();
//    }



//    public List<Category> getMiddleCategoryByParent(Long parentCategorySeq) { //대카테고리별 중카테고리조회
//        // parentSeq를 입력받아 parentCategorySeq에 해당하는 level =2 중분류 조회함
//        return jpaQueryFactory.selectFrom(qCategory)
//                .leftJoin(qCategory.parentCategorySeq)
//                .where(qCategory.level.eq(1) // 중분류인지 확인
//                        .and(qCategory.parentCategorySeq.categorySeq.eq(parentCategorySeq))) // 해당 대분류의 카테고리 Seq와 일치하는지 확인
//                .fetch();
//    }




}