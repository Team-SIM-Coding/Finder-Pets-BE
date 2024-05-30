package inf.saveanimals.domain.posts.sighted;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSightedComments is a Querydsl query type for SightedComments
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSightedComments extends EntityPathBase<SightedComments> {

    private static final long serialVersionUID = -1250630854L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSightedComments sightedComments = new QSightedComments("sightedComments");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> create_at = createDateTime("create_at", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QSightedPets sightedPets;

    public final StringPath user_image = createString("user_image");

    public final StringPath user_nickname = createString("user_nickname");

    public QSightedComments(String variable) {
        this(SightedComments.class, forVariable(variable), INITS);
    }

    public QSightedComments(Path<? extends SightedComments> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSightedComments(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSightedComments(PathMetadata metadata, PathInits inits) {
        this(SightedComments.class, metadata, inits);
    }

    public QSightedComments(Class<? extends SightedComments> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.sightedPets = inits.isInitialized("sightedPets") ? new QSightedPets(forProperty("sightedPets"), inits.get("sightedPets")) : null;
    }

}

