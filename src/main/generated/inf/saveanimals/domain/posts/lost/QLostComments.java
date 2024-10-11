package inf.saveanimals.domain.posts.lost;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLostComments is a Querydsl query type for LostComments
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLostComments extends EntityPathBase<LostComments> {

    private static final long serialVersionUID = -681046498L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLostComments lostComments = new QLostComments("lostComments");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> create_at = createDateTime("create_at", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<LostComments, QLostComments> lostChildren = this.<LostComments, QLostComments>createList("lostChildren", LostComments.class, QLostComments.class, PathInits.DIRECT2);

    public final QLostComments lostParent;

    public final QLostPets lostPets;

    public final inf.saveanimals.domain.users.QUser user;

    public final StringPath user_image = createString("user_image");

    public final StringPath user_nickname = createString("user_nickname");

    public QLostComments(String variable) {
        this(LostComments.class, forVariable(variable), INITS);
    }

    public QLostComments(Path<? extends LostComments> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLostComments(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLostComments(PathMetadata metadata, PathInits inits) {
        this(LostComments.class, metadata, inits);
    }

    public QLostComments(Class<? extends LostComments> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.lostParent = inits.isInitialized("lostParent") ? new QLostComments(forProperty("lostParent"), inits.get("lostParent")) : null;
        this.lostPets = inits.isInitialized("lostPets") ? new QLostPets(forProperty("lostPets"), inits.get("lostPets")) : null;
        this.user = inits.isInitialized("user") ? new inf.saveanimals.domain.users.QUser(forProperty("user")) : null;
    }

}

