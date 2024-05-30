package inf.saveanimals.domain.posts.lost;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLostImg is a Querydsl query type for LostImg
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLostImg extends EntityPathBase<LostImg> {

    private static final long serialVersionUID = -861244071L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLostImg lostImg = new QLostImg("lostImg");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgUrl = createString("imgUrl");

    public final EnumPath<inf.saveanimals.domain.posts.common.IsMainImg> isMainImg = createEnum("isMainImg", inf.saveanimals.domain.posts.common.IsMainImg.class);

    public final QLostPets lostPets;

    public QLostImg(String variable) {
        this(LostImg.class, forVariable(variable), INITS);
    }

    public QLostImg(Path<? extends LostImg> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLostImg(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLostImg(PathMetadata metadata, PathInits inits) {
        this(LostImg.class, metadata, inits);
    }

    public QLostImg(Class<? extends LostImg> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.lostPets = inits.isInitialized("lostPets") ? new QLostPets(forProperty("lostPets"), inits.get("lostPets")) : null;
    }

}

