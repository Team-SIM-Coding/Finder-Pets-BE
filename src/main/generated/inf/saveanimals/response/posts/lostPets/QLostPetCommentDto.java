package inf.saveanimals.response.posts.lostPets;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * inf.saveanimals.response.posts.lostPets.QLostPetCommentDto is a Querydsl Projection type for LostPetCommentDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QLostPetCommentDto extends ConstructorExpression<LostPetCommentDto> {

    private static final long serialVersionUID = -2064291252L;

    public QLostPetCommentDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<Long> userId, com.querydsl.core.types.Expression<String> user_nickname, com.querydsl.core.types.Expression<String> user_profile_image, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<java.time.LocalDateTime> createdAt) {
        super(LostPetCommentDto.class, new Class<?>[]{long.class, long.class, String.class, String.class, String.class, java.time.LocalDateTime.class}, id, userId, user_nickname, user_profile_image, content, createdAt);
    }

}

