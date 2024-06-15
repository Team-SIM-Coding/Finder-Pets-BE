package inf.saveanimals.domain.users;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1866596122L;

    public static final QUser user = new QUser("user");

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath img = createString("img");

    public final ListPath<inf.saveanimals.domain.posts.lost.LostPets, inf.saveanimals.domain.posts.lost.QLostPets> lostPets = this.<inf.saveanimals.domain.posts.lost.LostPets, inf.saveanimals.domain.posts.lost.QLostPets>createList("lostPets", inf.saveanimals.domain.posts.lost.LostPets.class, inf.saveanimals.domain.posts.lost.QLostPets.class, PathInits.DIRECT2);

    public final ListPath<inf.saveanimals.domain.animals.MyPets, inf.saveanimals.domain.animals.QMyPets> myPetsList = this.<inf.saveanimals.domain.animals.MyPets, inf.saveanimals.domain.animals.QMyPets>createList("myPetsList", inf.saveanimals.domain.animals.MyPets.class, inf.saveanimals.domain.animals.QMyPets.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath roles = createString("roles");

    public final ListPath<inf.saveanimals.domain.posts.sighted.SightedPets, inf.saveanimals.domain.posts.sighted.QSightedPets> sightedPets = this.<inf.saveanimals.domain.posts.sighted.SightedPets, inf.saveanimals.domain.posts.sighted.QSightedPets>createList("sightedPets", inf.saveanimals.domain.posts.sighted.SightedPets.class, inf.saveanimals.domain.posts.sighted.QSightedPets.class, PathInits.DIRECT2);

    public final EnumPath<UserLevel> userLevel = createEnum("userLevel", UserLevel.class);

    public final StringPath userTel = createString("userTel");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

