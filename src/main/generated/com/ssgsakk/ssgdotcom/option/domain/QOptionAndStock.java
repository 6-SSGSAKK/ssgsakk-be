package com.ssgsakk.ssgdotcom.option.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOptionAndStock is a Querydsl query type for OptionAndStock
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOptionAndStock extends EntityPathBase<OptionAndStock> {

    private static final long serialVersionUID = 809848186L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOptionAndStock optionAndStock = new QOptionAndStock("optionAndStock");

    public final com.ssgsakk.ssgdotcom.common.entity.QBaseCreateTimeEntity _super = new com.ssgsakk.ssgdotcom.common.entity.QBaseCreateTimeEntity(this);

    public final QColor color;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final QCustomizationOption customizationOption;

    public final NumberPath<Integer> minimumStock = createNumber("minimumStock", Integer.class);

    public final NumberPath<Long> optionAndStockSeq = createNumber("optionAndStockSeq", Long.class);

    public final NumberPath<Long> productSeq = createNumber("productSeq", Long.class);

    public final QSize size;

    public final NumberPath<Integer> stock = createNumber("stock", Integer.class);

    public QOptionAndStock(String variable) {
        this(OptionAndStock.class, forVariable(variable), INITS);
    }

    public QOptionAndStock(Path<? extends OptionAndStock> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOptionAndStock(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOptionAndStock(PathMetadata metadata, PathInits inits) {
        this(OptionAndStock.class, metadata, inits);
    }

    public QOptionAndStock(Class<? extends OptionAndStock> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.color = inits.isInitialized("color") ? new QColor(forProperty("color")) : null;
        this.customizationOption = inits.isInitialized("customizationOption") ? new QCustomizationOption(forProperty("customizationOption")) : null;
        this.size = inits.isInitialized("size") ? new QSize(forProperty("size")) : null;
    }

}

