package com.ssgsakk.ssgdotcom.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseCreateTimeEntity is a Querydsl query type for BaseCreateTimeEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseCreateTimeEntity extends EntityPathBase<BaseCreateTimeEntity> {

    private static final long serialVersionUID = 956961978L;

    public static final QBaseCreateTimeEntity baseCreateTimeEntity = new QBaseCreateTimeEntity("baseCreateTimeEntity");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public QBaseCreateTimeEntity(String variable) {
        super(BaseCreateTimeEntity.class, forVariable(variable));
    }

    public QBaseCreateTimeEntity(Path<? extends BaseCreateTimeEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseCreateTimeEntity(PathMetadata metadata) {
        super(BaseCreateTimeEntity.class, metadata);
    }

}

