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

    public List<Tuple> getBigCategory() { //대카테고리조회
        return jpaQueryFactory
                .select(qCategory.categorySeq, qCategory.level, qCategory.categoryName)
                .from(qCategory)
                .where(qCategory.level.eq(1))
                .fetch();
    }

    public List<Tuple> getMiddleCategoryByBig(Long parentCategoryId) { //대카테고리별 중카테고리 조회
        return jpaQueryFactory
                .select(qCategory.categorySeq, qCategory.categoryName, qCategory.level)
                .from(qCategory)
                .where(qCategory.parentCategorySeq.categorySeq.eq(parentCategoryId).and(qCategory.level.eq(2)))
                .fetch();
    }

    public List<Tuple> getSmallCategoryByMid(Long parentCategoryId) { //중카테고리별 소카테고리 조회
        return jpaQueryFactory
                .select(qCategory.categorySeq, qCategory.categoryName, qCategory.level)
                .from(qCategory)
                .where(qCategory.parentCategorySeq.categorySeq.eq(parentCategoryId).and(qCategory.level.eq(3)))
                .fetch();
    }


}