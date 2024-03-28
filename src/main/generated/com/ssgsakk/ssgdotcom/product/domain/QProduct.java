package com.ssgsakk.ssgdotcom.product.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = -1319270183L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProduct product = new QProduct("product");

    public final NumberPath<Double> averageRating = createNumber("averageRating", Double.class);

    public final EnumPath<com.ssgsakk.ssgdotcom.common.utils.DeliveryType> deliveryType = createEnum("deliveryType", com.ssgsakk.ssgdotcom.common.utils.DeliveryType.class);

    public final NumberPath<Integer> discountPercent = createNumber("discountPercent", Integer.class);

    public final StringPath productDescription = createString("productDescription");

    public final StringPath productName = createString("productName");

    public final NumberPath<Integer> productPrice = createNumber("productPrice", Integer.class);

    public final NumberPath<Long> productSeq = createNumber("productSeq", Long.class);

    public final NumberPath<Integer> reviewCount = createNumber("reviewCount", Integer.class);

    public final com.ssgsakk.ssgdotcom.vendor.domain.QVendor vendor;

    public QProduct(String variable) {
        this(Product.class, forVariable(variable), INITS);
    }

    public QProduct(Path<? extends Product> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProduct(PathMetadata metadata, PathInits inits) {
        this(Product.class, metadata, inits);
    }

    public QProduct(Class<? extends Product> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.vendor = inits.isInitialized("vendor") ? new com.ssgsakk.ssgdotcom.vendor.domain.QVendor(forProperty("vendor")) : null;
    }

}

