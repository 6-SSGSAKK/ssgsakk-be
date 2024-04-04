package com.ssgsakk.ssgdotcom.product.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductList is a Querydsl query type for ProductList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductList extends EntityPathBase<ProductList> {

    private static final long serialVersionUID = 1131387159L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductList productList = new QProductList("productList");

    public final com.ssgsakk.ssgdotcom.category.domain.QCategory category;

    public final QProduct product;

    public final NumberPath<Long> productListSeq = createNumber("productListSeq", Long.class);

    public QProductList(String variable) {
        this(ProductList.class, forVariable(variable), INITS);
    }

    public QProductList(Path<? extends ProductList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductList(PathMetadata metadata, PathInits inits) {
        this(ProductList.class, metadata, inits);
    }

    public QProductList(Class<? extends ProductList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new com.ssgsakk.ssgdotcom.category.domain.QCategory(forProperty("category"), inits.get("category")) : null;
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

