package com.ssgsakk.ssgdotcom.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class QueryDslConfig {

    @PersistenceContext // EntityManager를 빈으로 주입할 때 사용하는 어노테이션
    private EntityManager entityManager;
    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }
}


