package com.ssgsakk.ssgdotcom.option.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSize is a Querydsl query type for Size
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSize extends EntityPathBase<Size> {

    private static final long serialVersionUID = 297538023L;

    public static final QSize size = new QSize("size1");

    public final StringPath sizeData = createString("sizeData");

    public final NumberPath<Long> sizeSeq = createNumber("sizeSeq", Long.class);

    public QSize(String variable) {
        super(Size.class, forVariable(variable));
    }

    public QSize(Path<? extends Size> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSize(PathMetadata metadata) {
        super(Size.class, metadata);
    }

}

