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

    public QSightedPetsThumbnailResponse(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<inf.saveanimals.domain.posts.common.Category> category, com.querydsl.core.types.Expression<inf.saveanimals.domain.posts.common.IsCompleted> isCompleted, com.querydsl.core.types.Expression<java.time.LocalDateTime> foundDate, com.querydsl.core.types.Expression<java.time.LocalDateTime> createdAt, com.querydsl.core.types.Expression<String> foundPlace, com.querydsl.core.types.Expression<inf.saveanimals.domain.areas.City> city, com.querydsl.core.types.Expression<inf.saveanimals.domain.areas.Districts> districts, com.querydsl.core.types.Expression<inf.saveanimals.domain.animals.common.BreedGroup> breedGroup, com.querydsl.core.types.Expression<inf.saveanimals.domain.animals.common.Breed> breed, com.querydsl.core.types.Expression<String> thumbnailImgPath, com.querydsl.core.types.Expression<Integer> views, com.querydsl.core.types.Expression<Integer> totalLike) {
        super(SightedPetsThumbnailResponse.class, new Class<?>[]{long.class, inf.saveanimals.domain.posts.common.Category.class, inf.saveanimals.domain.posts.common.IsCompleted.class, java.time.LocalDateTime.class, java.time.LocalDateTime.class, String.class, inf.saveanimals.domain.areas.City.class, inf.saveanimals.domain.areas.Districts.class, inf.saveanimals.domain.animals.common.BreedGroup.class, inf.saveanimals.domain.animals.common.Breed.class, String.class, int.class, int.class}, id, category, isCompleted, foundDate, createdAt, foundPlace, city, districts, breedGroup, breed, thumbnailImgPath, views, totalLike);
    }

}

