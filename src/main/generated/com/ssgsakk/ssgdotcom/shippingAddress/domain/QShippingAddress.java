package com.ssgsakk.ssgdotcom.shippingAddress.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QShippingAddress is a Querydsl query type for ShippingAddress
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShippingAddress extends EntityPathBase<ShippingAddress> {

    private static final long serialVersionUID = -1051909127L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShippingAddress shippingAddress = new QShippingAddress("shippingAddress");

    public final StringPath addressNickname = createString("addressNickname");

    public final NumberPath<Integer> defaultAddressCheck = createNumber("defaultAddressCheck", Integer.class);

    public final StringPath detailAddress = createString("detailAddress");

    public final StringPath jibunAddress = createString("jibunAddress");

    public final StringPath receiverMobileNum = createString("receiverMobileNum");

    public final StringPath receiverName = createString("receiverName");

    public final StringPath roadAddress = createString("roadAddress");

    public final NumberPath<Long> shippingAddressSeq = createNumber("shippingAddressSeq", Long.class);

    public final com.ssgsakk.ssgdotcom.member.domain.QUser user;

    public final StringPath zipCode = createString("zipCode");

    public QShippingAddress(String variable) {
        this(ShippingAddress.class, forVariable(variable), INITS);
    }

    public QShippingAddress(Path<? extends ShippingAddress> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QShippingAddress(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QShippingAddress(PathMetadata metadata, PathInits inits) {
        this(ShippingAddress.class, metadata, inits);
    }

    public QShippingAddress(Class<? extends ShippingAddress> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.ssgsakk.ssgdotcom.member.domain.QUser(forProperty("user")) : null;
    }

}

