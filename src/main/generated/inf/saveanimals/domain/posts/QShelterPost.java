package inf.saveanimals.domain.posts;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QShelterPost is a Querydsl query type for ShelterPost
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShelterPost extends EntityPathBase<ShelterPost> {

    private static final long serialVersionUID = 355650113L;

    public static final QShelterPost shelterPost = new QShelterPost("shelterPost");

    public final StringPath age = createString("age");

    public final StringPath careAddr = createString("careAddr");

    public final StringPath careNm = createString("careNm");

    public final StringPath careTel = createString("careTel");

    public final StringPath chargeNm = createString("chargeNm");

    public final StringPath colorCd = createString("colorCd");

    public final StringPath happenDt = createString("happenDt");

    public final StringPath happenPlace = createString("happenPlace");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath kindCd = createString("kindCd");

    public final StringPath neuterYn = createString("neuterYn");

    public final StringPath officetel = createString("officetel");

    public final StringPath processStatus = createString("processStatus");

    public final StringPath profile = createString("profile");

    public final StringPath sexCd = createString("sexCd");

    public final StringPath specialMark = createString("specialMark");

    public final StringPath weight = createString("weight");

    public QShelterPost(String variable) {
        super(ShelterPost.class, forVariable(variable));
    }

    public QShelterPost(Path<? extends ShelterPost> path) {
        super(path.getType(), path.getMetadata());
    }

    public QShelterPost(PathMetadata metadata) {
        super(ShelterPost.class, metadata);
    }

}

