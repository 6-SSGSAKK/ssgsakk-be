package com.ssgsakk.ssgdotcom.member.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOAuth is a Querydsl query type for OAuth
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOAuth extends EntityPathBase<OAuth> {

    private static final long serialVersionUID = -1121652852L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOAuth oAuth = new QOAuth("oAuth");

    public final com.ssgsakk.ssgdotcom.common.entity.QBaseTimeEntity _super = new com.ssgsakk.ssgdotcom.common.entity.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath oauthId = createString("oauthId");

    public final NumberPath<Long> oauthSeq = createNumber("oauthSeq", Long.class);

    public final StringPath oauthType = createString("oauthType");

    public final QUser user;

    public QOAuth(String variable) {
        this(OAuth.class, forVariable(variable), INITS);
    }

    public QOAuth(Path<? extends OAuth> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOAuth(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOAuth(PathMetadata metadata, PathInits inits) {
        this(OAuth.class, metadata, inits);
    }

    public QOAuth(Class<? extends OAuth> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

