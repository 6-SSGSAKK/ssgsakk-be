package com.ssgsakk.ssgdotcom.cart;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCart is a Querydsl query type for Cart
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCart extends EntityPathBase<Cart> {

    private static final long serialVersionUID = -410130727L;

    public static final QCart cart = new QCart("cart");

    public final com.ssgsakk.ssgdotcom.common.entity.QBaseTimeEntity _super = new com.ssgsakk.ssgdotcom.common.entity.QBaseTimeEntity(this);

    public final NumberPath<Long> cartSeq = createNumber("cartSeq", Long.class);

    public final NumberPath<Short> checkbox = createNumber("checkbox", Short.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Short> fix_item = createNumber("fix_item", Short.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public QCart(String variable) {
        super(Cart.class, forVariable(variable));
    }

    public QCart(Path<? extends Cart> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCart(PathMetadata metadata) {
        super(Cart.class, metadata);
    }

}

