package inf.saveanimals.domain.animals;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMyPets is a Querydsl query type for MyPets
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMyPets extends EntityPathBase<MyPets> {

    private static final long serialVersionUID = 1465355648L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMyPets myPets = new QMyPets("myPets");

    public final DateTimePath<java.time.LocalDateTime> adoptionDay = createDateTime("adoptionDay", java.time.LocalDateTime.class);

    public final StringPath age = createString("age");

    public final DateTimePath<java.time.LocalDateTime> birthDay = createDateTime("birthDay", java.time.LocalDateTime.class);

    public final EnumPath<inf.saveanimals.domain.animals.common.Breed> breed = createEnum("breed", inf.saveanimals.domain.animals.common.Breed.class);

    public final StringPath color = createString("color");

    public final EnumPath<inf.saveanimals.domain.animals.common.Gender> gender = createEnum("gender", inf.saveanimals.domain.animals.common.Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isAdoption = createBoolean("isAdoption");

    public final StringPath name = createString("name");

    public final EnumPath<inf.saveanimals.domain.animals.common.NeuteringStatus> neuteringStatus = createEnum("neuteringStatus", inf.saveanimals.domain.animals.common.NeuteringStatus.class);

    public final StringPath petImage = createString("petImage");

    public final StringPath petOwnerTel = createString("petOwnerTel");

    public final StringPath specialMark = createString("specialMark");

    public final inf.saveanimals.domain.users.QUser user;

    public final NumberPath<Float> weight = createNumber("weight", Float.class);

    public QMyPets(String variable) {
        this(MyPets.class, forVariable(variable), INITS);
    }

    public QMyPets(Path<? extends MyPets> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMyPets(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMyPets(PathMetadata metadata, PathInits inits) {
        this(MyPets.class, metadata, inits);
    }

    public QMyPets(Class<? extends MyPets> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new inf.saveanimals.domain.users.QUser(forProperty("user")) : null;
    }

}

