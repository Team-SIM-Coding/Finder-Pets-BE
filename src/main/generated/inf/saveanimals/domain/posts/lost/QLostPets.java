package inf.saveanimals.domain.posts.lost;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLostPets is a Querydsl query type for LostPets
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLostPets extends EntityPathBase<LostPets> {

    private static final long serialVersionUID = -928561058L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLostPets lostPets = new QLostPets("lostPets");

    public final StringPath age = createString("age");

    public final EnumPath<inf.saveanimals.domain.animals.common.Breed> breed = createEnum("breed", inf.saveanimals.domain.animals.common.Breed.class);

    public final EnumPath<inf.saveanimals.domain.animals.common.BreedGroup> breedGroup = createEnum("breedGroup", inf.saveanimals.domain.animals.common.BreedGroup.class);

    public final EnumPath<inf.saveanimals.domain.posts.common.Category> category = createEnum("category", inf.saveanimals.domain.posts.common.Category.class);

    public final EnumPath<inf.saveanimals.domain.areas.City> city = createEnum("city", inf.saveanimals.domain.areas.City.class);

    public final StringPath color = createString("color");

    public final ListPath<LostComments, SimplePath<LostComments>> comments = this.<LostComments, SimplePath<LostComments>>createList("comments", LostComments.class, SimplePath.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    public final EnumPath<inf.saveanimals.domain.areas.Districts> districts = createEnum("districts", inf.saveanimals.domain.areas.Districts.class);

    public final EnumPath<inf.saveanimals.domain.animals.common.Gender> gender = createEnum("gender", inf.saveanimals.domain.animals.common.Gender.class);

    public final StringPath happenPlace = createString("happenPlace");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<inf.saveanimals.domain.posts.common.IsCompleted> isCompleted = createEnum("isCompleted", inf.saveanimals.domain.posts.common.IsCompleted.class);

    public final StringPath latitude = createString("latitude");

    public final StringPath longitude = createString("longitude");

    public final ListPath<LostImg, QLostImg> lostImgs = this.<LostImg, QLostImg>createList("lostImgs", LostImg.class, QLostImg.class, PathInits.DIRECT2);

    public final EnumPath<inf.saveanimals.domain.animals.common.NeuteringStatus> neuteringStatus = createEnum("neuteringStatus", inf.saveanimals.domain.animals.common.NeuteringStatus.class);

    public final StringPath petOwnerTel = createString("petOwnerTel");

    public final StringPath specialMark = createString("specialMark");

    public final NumberPath<Integer> totalLike = createNumber("totalLike", Integer.class);

    public final inf.saveanimals.domain.users.QUser user;

    public final NumberPath<Integer> views = createNumber("views", Integer.class);

    public final NumberPath<Float> weight = createNumber("weight", Float.class);

    public QLostPets(String variable) {
        this(LostPets.class, forVariable(variable), INITS);
    }

    public QLostPets(Path<? extends LostPets> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLostPets(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLostPets(PathMetadata metadata, PathInits inits) {
        this(LostPets.class, metadata, inits);
    }

    public QLostPets(Class<? extends LostPets> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new inf.saveanimals.domain.users.QUser(forProperty("user")) : null;
    }

}

