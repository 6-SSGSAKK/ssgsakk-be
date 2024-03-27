package com.ssgsakk.ssgdotcom.order.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOrder is a Querydsl query type for Order
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrder extends EntityPathBase<Order> {

    private static final long serialVersionUID = 1024585465L;

    public static final QOrder order = new QOrder("order1");

    public final StringPath deliverymessage = createString("deliverymessage");

    public final StringPath finalAsdress = createString("finalAsdress");

    public final StringPath finalDetailAddress = createString("finalDetailAddress");

    public final StringPath finalJibunAddress = createString("finalJibunAddress");

    public final StringPath finalRoadAddress = createString("finalRoadAddress");

    public final DateTimePath<java.sql.Timestamp> orderAt = createDateTime("orderAt", java.sql.Timestamp.class);

    public final StringPath orderer = createString("orderer");

    public final StringPath ordererEmail = createString("ordererEmail");

    public final StringPath ordererPhoneNum = createString("ordererPhoneNum");

    public final NumberPath<Long> orderSeq = createNumber("orderSeq", Long.class);

    public final StringPath recipient = createString("recipient");

    public final StringPath recipientEmail = createString("recipientEmail");

    public final StringPath recipientPhoneNum = createString("recipientPhoneNum");

    public QOrder(String variable) {
        super(Order.class, forVariable(variable));
    }

    public QOrder(Path<? extends Order> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrder(PathMetadata metadata) {
        super(Order.class, metadata);
    }

}

