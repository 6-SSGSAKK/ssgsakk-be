package com.ssgsakk.ssgdotcom.category.infrastructure;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssgsakk.ssgdotcom.category.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {



    @Query("select c from Category c where c.parentCategorySeq is null ") //전체카테고리 조회
    List<Category> findByParentCategorySeq();




}


