package inf.saveanimals.response.posts.sightedPets;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * inf.saveanimals.response.posts.sightedPets.QSightedPetsThumbnailResponse is a Querydsl Projection type for SightedPetsThumbnailResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QSightedPetsThumbnailResponse extends ConstructorExpression<SightedPetsThumbnailResponse> {

    private static final long serialVersionUID = -1276184254L;

    public QSightedPetsThumbnailResponse(com.querydsl.core.types.Expression<Long> pet_id, com.querydsl.core.types.Expression<inf.saveanimals.domain.posts.common.Category> category, com.querydsl.core.types.Expression<inf.saveanimals.domain.posts.common.IsCompleted> isCompleted, com.querydsl.core.types.Expression<java.time.LocalDateTime> date, com.querydsl.core.types.Expression<java.time.LocalDateTime> created_at, com.querydsl.core.types.Expression<String> area, com.querydsl.core.types.Expression<inf.saveanimals.domain.areas.City> city, com.querydsl.core.types.Expression<inf.saveanimals.domain.areas.Districts> districts, com.querydsl.core.types.Expression<inf.saveanimals.domain.animals.common.BreedGroup> animal, com.querydsl.core.types.Expression<inf.saveanimals.domain.animals.common.Breed> kind, com.querydsl.core.types.Expression<String> img_url, com.querydsl.core.types.Expression<Integer> views, com.querydsl.core.types.Expression<Integer> total_like) {
        super(SightedPetsThumbnailResponse.class, new Class<?>[]{long.class, inf.saveanimals.domain.posts.common.Category.class, inf.saveanimals.domain.posts.common.IsCompleted.class, java.time.LocalDateTime.class, java.time.LocalDateTime.class, String.class, inf.saveanimals.domain.areas.City.class, inf.saveanimals.domain.areas.Districts.class, inf.saveanimals.domain.animals.common.BreedGroup.class, inf.saveanimals.domain.animals.common.Breed.class, String.class, int.class, int.class}, pet_id, category, isCompleted, date, created_at, area, city, districts, animal, kind, img_url, views, total_like);
    }

}

