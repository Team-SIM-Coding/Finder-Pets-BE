package inf.saveanimals.response.posts.lostPets;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * inf.saveanimals.response.posts.lostPets.QLostPetsThumbnailResponse is a Querydsl Projection type for LostPetsThumbnailResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QLostPetsThumbnailResponse extends ConstructorExpression<LostPetsThumbnailResponse> {

    private static final long serialVersionUID = 517145670L;

    public QLostPetsThumbnailResponse(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<inf.saveanimals.domain.posts.common.Category> category, com.querydsl.core.types.Expression<inf.saveanimals.domain.posts.common.IsCompleted> isCompleted, com.querydsl.core.types.Expression<inf.saveanimals.domain.animals.common.Breed> breed, com.querydsl.core.types.Expression<inf.saveanimals.domain.animals.common.BreedGroup> breedGroup, com.querydsl.core.types.Expression<inf.saveanimals.domain.animals.common.Gender> gender, com.querydsl.core.types.Expression<String> weight, com.querydsl.core.types.Expression<String> specialMark, com.querydsl.core.types.Expression<String> happenPlace, com.querydsl.core.types.Expression<String> thumbnailImgPath, com.querydsl.core.types.Expression<Integer> views, com.querydsl.core.types.Expression<Integer> totalLike) {
        super(LostPetsThumbnailResponse.class, new Class<?>[]{long.class, inf.saveanimals.domain.posts.common.Category.class, inf.saveanimals.domain.posts.common.IsCompleted.class, inf.saveanimals.domain.animals.common.Breed.class, inf.saveanimals.domain.animals.common.BreedGroup.class, inf.saveanimals.domain.animals.common.Gender.class, String.class, String.class, String.class, String.class, int.class, int.class}, id, category, isCompleted, breed, breedGroup, gender, weight, specialMark, happenPlace, thumbnailImgPath, views, totalLike);
    }

}

