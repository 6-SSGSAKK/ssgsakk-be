package com.ssgsakk.ssgdotcom.category.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCategory is a Querydsl query type for Category
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCategory extends EntityPathBase<Category> {

    private static final long serialVersionUID = -1255813843L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCategory category = new QCategory("category");

    public final StringPath categoryName = createString("categoryName");

    public final NumberPath<Long> categorySeq = createNumber("categorySeq", Long.class);

    public final ListPath<Category, QCategory> child = this.<Category, QCategory>createList("child", Category.class, QCategory.class, PathInits.DIRECT2);

    public final NumberPath<Integer> level = createNumber("level", Integer.class);

    public final QCategory parentCategorySeq;

    public QCategory(String variable) {
        this(Category.class, forVariable(variable), INITS);
    }

    public QCategory(Path<? extends Category> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCategory(PathMetadata metadata, PathInits inits) {
        this(Category.class, metadata, inits);
    }

    public QCategory(Class<? extends Category> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parentCategorySeq = inits.isInitialized("parentCategorySeq") ? new QCategory(forProperty("parentCategorySeq"), inits.get("parentCategorySeq")) : null;
    }

}

