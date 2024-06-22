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

    public QLostPetsThumbnailResponse(com.querydsl.core.types.Expression<Long> pet_id, com.querydsl.core.types.Expression<inf.saveanimals.domain.posts.common.Category> category, com.querydsl.core.types.Expression<inf.saveanimals.domain.posts.common.IsCompleted> isCompleted, com.querydsl.core.types.Expression<inf.saveanimals.domain.animals.common.Breed> kind, com.querydsl.core.types.Expression<inf.saveanimals.domain.animals.common.BreedGroup> animal, com.querydsl.core.types.Expression<inf.saveanimals.domain.animals.common.Gender> gender, com.querydsl.core.types.Expression<String> weight, com.querydsl.core.types.Expression<String> character, com.querydsl.core.types.Expression<String> area, com.querydsl.core.types.Expression<String> img_url, com.querydsl.core.types.Expression<Integer> views, com.querydsl.core.types.Expression<Integer> total_like) {
        super(LostPetsThumbnailResponse.class, new Class<?>[]{long.class, inf.saveanimals.domain.posts.common.Category.class, inf.saveanimals.domain.posts.common.IsCompleted.class, inf.saveanimals.domain.animals.common.Breed.class, inf.saveanimals.domain.animals.common.BreedGroup.class, inf.saveanimals.domain.animals.common.Gender.class, String.class, String.class, String.class, String.class, int.class, int.class}, pet_id, category, isCompleted, kind, animal, gender, weight, character, area, img_url, views, total_like);
    }

}

