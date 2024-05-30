package inf.saveanimals.domain.posts.sighted;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSightedLike is a Querydsl query type for SightedLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSightedLike extends EntityPathBase<SightedLike> {

    private static final long serialVersionUID = 1858160605L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSightedLike sightedLike = new QSightedLike("sightedLike");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QSightedPets sightedPets;

    public final inf.saveanimals.domain.users.QUser user;

    public QSightedLike(String variable) {
        this(SightedLike.class, forVariable(variable), INITS);
    }

    public QSightedLike(Path<? extends SightedLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSightedLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSightedLike(PathMetadata metadata, PathInits inits) {
        this(SightedLike.class, metadata, inits);
    }

    public QSightedLike(Class<? extends SightedLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.sightedPets = inits.isInitialized("sightedPets") ? new QSightedPets(forProperty("sightedPets"), inits.get("sightedPets")) : null;
        this.user = inits.isInitialized("user") ? new inf.saveanimals.domain.users.QUser(forProperty("user")) : null;
    }

}

