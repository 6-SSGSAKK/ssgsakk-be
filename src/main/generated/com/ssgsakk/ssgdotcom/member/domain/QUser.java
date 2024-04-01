package com.ssgsakk.ssgdotcom.member.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1488064598L;

    public static final QUser user = new QUser("user");

    public final com.ssgsakk.ssgdotcom.common.entity.QBaseTimeEntity _super = new com.ssgsakk.ssgdotcom.common.entity.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath name = createString("name");

    public final ListPath<OAuth, QOAuth> oAuths = this.<OAuth, QOAuth>createList("oAuths", OAuth.class, QOAuth.class, PathInits.DIRECT2);

    public final NumberPath<Integer> state = createNumber("state", Integer.class);

    public final StringPath userEmail = createString("userEmail");

    public final StringPath userId = createString("userId");

    public final StringPath userMobileNum = createString("userMobileNum");

    public final StringPath userPassword = createString("userPassword");

    public final NumberPath<Long> userSeq = createNumber("userSeq", Long.class);

    public final StringPath uuid = createString("uuid");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

