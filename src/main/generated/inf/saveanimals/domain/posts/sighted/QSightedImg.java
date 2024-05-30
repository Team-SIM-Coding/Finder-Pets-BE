package inf.saveanimals.domain.posts.sighted;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSightedImg is a Querydsl query type for SightedImg
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSightedImg extends EntityPathBase<SightedImg> {

    private static final long serialVersionUID = -1464082755L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSightedImg sightedImg = new QSightedImg("sightedImg");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgUrl = createString("imgUrl");

    public final EnumPath<inf.saveanimals.domain.posts.common.IsMainImg> isMainImg = createEnum("isMainImg", inf.saveanimals.domain.posts.common.IsMainImg.class);

    public final QSightedPets sightedPets;

    public QSightedImg(String variable) {
        this(SightedImg.class, forVariable(variable), INITS);
    }

    public QSightedImg(Path<? extends SightedImg> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSightedImg(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSightedImg(PathMetadata metadata, PathInits inits) {
        this(SightedImg.class, metadata, inits);
    }

    public QSightedImg(Class<? extends SightedImg> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.sightedPets = inits.isInitialized("sightedPets") ? new QSightedPets(forProperty("sightedPets"), inits.get("sightedPets")) : null;
    }

}

