package com.sejong.test.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductSearchHistory is a Querydsl query type for ProductSearchHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductSearchHistory extends EntityPathBase<ProductSearchHistory> {

    private static final long serialVersionUID = 1479031932L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductSearchHistory productSearchHistory = new QProductSearchHistory("productSearchHistory");

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    public final QProduct product;

    public final DateTimePath<java.time.LocalDateTime> searchTime = createDateTime("searchTime", java.time.LocalDateTime.class);

    public final QUser user;

    public QProductSearchHistory(String variable) {
        this(ProductSearchHistory.class, forVariable(variable), INITS);
    }

    public QProductSearchHistory(Path<? extends ProductSearchHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductSearchHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductSearchHistory(PathMetadata metadata, PathInits inits) {
        this(ProductSearchHistory.class, metadata, inits);
    }

    public QProductSearchHistory(Class<? extends ProductSearchHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

