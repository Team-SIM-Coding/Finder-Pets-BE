package inf.saveanimals.domain.posts.sighted;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSightedPets is a Querydsl query type for SightedPets
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSightedPets extends EntityPathBase<SightedPets> {

    private static final long serialVersionUID = 1858276218L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSightedPets sightedPets = new QSightedPets("sightedPets");

    public final StringPath age = createString("age");

    public final EnumPath<inf.saveanimals.domain.animals.common.Breed> breed = createEnum("breed", inf.saveanimals.domain.animals.common.Breed.class);

    public final EnumPath<inf.saveanimals.domain.animals.common.BreedGroup> breedGroup = createEnum("breedGroup", inf.saveanimals.domain.animals.common.BreedGroup.class);

    public final EnumPath<inf.saveanimals.domain.posts.common.Category> category = createEnum("category", inf.saveanimals.domain.posts.common.Category.class);

    public final EnumPath<inf.saveanimals.domain.areas.City> city = createEnum("city", inf.saveanimals.domain.areas.City.class);

    public final StringPath color = createString("color");

    public final ListPath<SightedComments, QSightedComments> comments = this.<SightedComments, QSightedComments>createList("comments", SightedComments.class, QSightedComments.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath detailed = createString("detailed");

    public final EnumPath<inf.saveanimals.domain.areas.District> district = createEnum("district", inf.saveanimals.domain.areas.District.class);

    public final DateTimePath<java.time.LocalDateTime> foundDate = createDateTime("foundDate", java.time.LocalDateTime.class);

    public final StringPath foundPlace = createString("foundPlace");

    public final EnumPath<inf.saveanimals.domain.animals.common.Gender> gender = createEnum("gender", inf.saveanimals.domain.animals.common.Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<inf.saveanimals.domain.posts.common.IsCompleted> isCompleted = createEnum("isCompleted", inf.saveanimals.domain.posts.common.IsCompleted.class);

    public final StringPath latitude = createString("latitude");

    public final StringPath longitude = createString("longitude");

    public final EnumPath<inf.saveanimals.domain.animals.common.NeuteringStatus> neuteringStatus = createEnum("neuteringStatus", inf.saveanimals.domain.animals.common.NeuteringStatus.class);

    public final StringPath reporterTel = createString("reporterTel");

    public final ListPath<SightedImg, QSightedImg> sightedImgList = this.<SightedImg, QSightedImg>createList("sightedImgList", SightedImg.class, QSightedImg.class, PathInits.DIRECT2);

    public final StringPath specialMark = createString("specialMark");

    public final NumberPath<Integer> totalLike = createNumber("totalLike", Integer.class);

    public final inf.saveanimals.domain.users.QUser user;

    public final NumberPath<Integer> views = createNumber("views", Integer.class);

    public final StringPath weight = createString("weight");

    public final StringPath writerNickname = createString("writerNickname");

    public final StringPath writerProfileImage = createString("writerProfileImage");

    public QSightedPets(String variable) {
        this(SightedPets.class, forVariable(variable), INITS);
    }

    public QSightedPets(Path<? extends SightedPets> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSightedPets(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSightedPets(PathMetadata metadata, PathInits inits) {
        this(SightedPets.class, metadata, inits);
    }

    public QSightedPets(Class<? extends SightedPets> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new inf.saveanimals.domain.users.QUser(forProperty("user")) : null;
    }

}

