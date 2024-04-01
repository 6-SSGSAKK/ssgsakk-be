package com.ssgsakk.ssgdotcom.option.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCustomizationOption is a Querydsl query type for CustomizationOption
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCustomizationOption extends EntityPathBase<CustomizationOption> {

    private static final long serialVersionUID = 921614210L;

    public static final QCustomizationOption customizationOption = new QCustomizationOption("customizationOption");

    public final StringPath customizationData = createString("customizationData");

    public final NumberPath<Long> customizationOptionSeq = createNumber("customizationOptionSeq", Long.class);

    public final StringPath customizationType = createString("customizationType");

    public QCustomizationOption(String variable) {
        super(CustomizationOption.class, forVariable(variable));
    }

    public QCustomizationOption(Path<? extends CustomizationOption> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCustomizationOption(PathMetadata metadata) {
        super(CustomizationOption.class, metadata);
    }

}

