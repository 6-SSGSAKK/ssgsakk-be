package com.ssgsakk.ssgdotcom.shippingAddress.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QShippingAddress is a Querydsl query type for ShippingAddress
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShippingAddress extends EntityPathBase<ShippingAddress> {

    private static final long serialVersionUID = -1051909127L;

    public static final QShippingAddress shippingAddress = new QShippingAddress("shippingAddress");

    public final com.ssgsakk.ssgdotcom.common.entity.QBaseTimeEntity _super = new com.ssgsakk.ssgdotcom.common.entity.QBaseTimeEntity(this);

    public final StringPath addressNickname = createString("addressNickname");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Integer> defaultAddressCheck = createNumber("defaultAddressCheck", Integer.class);

    public final StringPath detailAddress = createString("detailAddress");

    public final StringPath jibunAddress = createString("jibunAddress");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath receiverMobileNum = createString("receiverMobileNum");

    public final StringPath receiverName = createString("receiverName");

    public final StringPath roadAddress = createString("roadAddress");

    public final NumberPath<Long> shippingAddressSeq = createNumber("shippingAddressSeq", Long.class);

    public final StringPath uuid = createString("uuid");

    public final StringPath zipCode = createString("zipCode");

    public QShippingAddress(String variable) {
        super(ShippingAddress.class, forVariable(variable));
    }

    public QShippingAddress(Path<? extends ShippingAddress> path) {
        super(path.getType(), path.getMetadata());
    }

    public QShippingAddress(PathMetadata metadata) {
        super(ShippingAddress.class, metadata);
    }

}

