package com.ssgsakk.ssgdotcom.contents.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductContents is a Querydsl query type for ProductContents
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductContents extends EntityPathBase<ProductContents> {

    private static final long serialVersionUID = 373202046L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductContents productContents = new QProductContents("productContents");

    public final QContents contents;

    public final NumberPath<Integer> priority = createNumber("priority", Integer.class);

    public final com.ssgsakk.ssgdotcom.product.domain.QProduct product;

    public final NumberPath<Long> productContentsSeq = createNumber("productContentsSeq", Long.class);

    public QProductContents(String variable) {
        this(ProductContents.class, forVariable(variable), INITS);
    }

    public QProductContents(Path<? extends ProductContents> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductContents(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductContents(PathMetadata metadata, PathInits inits) {
        this(ProductContents.class, metadata, inits);
    }

    public QProductContents(Class<? extends ProductContents> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.contents = inits.isInitialized("contents") ? new QContents(forProperty("contents")) : null;
        this.product = inits.isInitialized("product") ? new com.ssgsakk.ssgdotcom.product.domain.QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

