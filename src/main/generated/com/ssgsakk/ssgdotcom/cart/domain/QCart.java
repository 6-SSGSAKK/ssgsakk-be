package com.ssgsakk.ssgdotcom.cart.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCart is a Querydsl query type for Cart
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCart extends EntityPathBase<Cart> {

    private static final long serialVersionUID = -1354717071L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCart cart = new QCart("cart");

    public final com.ssgsakk.ssgdotcom.common.entity.QBaseTimeEntity _super = new com.ssgsakk.ssgdotcom.common.entity.QBaseTimeEntity(this);

    public final NumberPath<Long> cartSeq = createNumber("cartSeq", Long.class);

    public final NumberPath<Short> checkbox = createNumber("checkbox", Short.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Short> fix_item = createNumber("fix_item", Short.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final com.ssgsakk.ssgdotcom.option.domain.QOptionAndStock optionAndStock;

    public final com.ssgsakk.ssgdotcom.product.domain.QProduct product;

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public final com.ssgsakk.ssgdotcom.member.domain.QUser user;

    public QCart(String variable) {
        this(Cart.class, forVariable(variable), INITS);
    }

    public QCart(Path<? extends Cart> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCart(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCart(PathMetadata metadata, PathInits inits) {
        this(Cart.class, metadata, inits);
    }

    public QCart(Class<? extends Cart> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.optionAndStock = inits.isInitialized("optionAndStock") ? new com.ssgsakk.ssgdotcom.option.domain.QOptionAndStock(forProperty("optionAndStock"), inits.get("optionAndStock")) : null;
        this.product = inits.isInitialized("product") ? new com.ssgsakk.ssgdotcom.product.domain.QProduct(forProperty("product"), inits.get("product")) : null;
        this.user = inits.isInitialized("user") ? new com.ssgsakk.ssgdotcom.member.domain.QUser(forProperty("user")) : null;
    }

}

