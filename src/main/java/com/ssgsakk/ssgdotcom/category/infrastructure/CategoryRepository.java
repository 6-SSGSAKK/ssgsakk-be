package com.ssgsakk.ssgdotcom.category.infrastructure;

import com.ssgsakk.ssgdotcom.category.domain.Category;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {
    private final EntityManager em;

    public List<Category> findAll(){
        return em.createQuery("select c from Category  c where  " +
                "c.parentCategorySeq is Null", Category.class).getResultList();
    }
}

