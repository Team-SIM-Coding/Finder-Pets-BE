package inf.saveanimals.response.posts.sightedPets;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * inf.saveanimals.response.posts.sightedPets.QSightedPetsDetailResponse is a Querydsl Projection type for SightedPetsDetailResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QSightedPetsDetailResponse extends ConstructorExpression<SightedPetsDetailResponse> {

    private static final long serialVersionUID = 1431789533L;

    public QSightedPetsDetailResponse(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> authorNick, com.querydsl.core.types.Expression<String> authorImg, com.querydsl.core.types.Expression<inf.saveanimals.domain.posts.common.Category> category, com.querydsl.core.types.Expression<inf.saveanimals.domain.posts.common.IsCompleted> isCompleted, com.querydsl.core.types.Expression<java.time.LocalDateTime> foundDate, com.querydsl.core.types.Expression<java.time.LocalDateTime> createdAt, com.querydsl.core.types.Expression<String> foundPlace, com.querydsl.core.types.Expression<String> specialMark, com.querydsl.core.types.Expression<String> reporterTel, com.querydsl.core.types.Expression<String> latitude, com.querydsl.core.types.Expression<String> longitude, com.querydsl.core.types.Expression<inf.saveanimals.domain.areas.City> city, com.querydsl.core.types.Expression<inf.saveanimals.domain.areas.Districts> districts, com.querydsl.core.types.Expression<inf.saveanimals.domain.animals.common.BreedGroup> breedGroup, com.querydsl.core.types.Expression<inf.saveanimals.domain.animals.common.Breed> breed, com.querydsl.core.types.Expression<? extends java.util.List<String>> imgPaths, com.querydsl.core.types.Expression<Integer> views, com.querydsl.core.types.Expression<Integer> totalLike) {
        super(SightedPetsDetailResponse.class, new Class<?>[]{long.class, String.class, String.class, inf.saveanimals.domain.posts.common.Category.class, inf.saveanimals.domain.posts.common.IsCompleted.class, java.time.LocalDateTime.class, java.time.LocalDateTime.class, String.class, String.class, String.class, String.class, String.class, inf.saveanimals.domain.areas.City.class, inf.saveanimals.domain.areas.Districts.class, inf.saveanimals.domain.animals.common.BreedGroup.class, inf.saveanimals.domain.animals.common.Breed.class, java.util.List.class, int.class, int.class}, id, authorNick, authorImg, category, isCompleted, foundDate, createdAt, foundPlace, specialMark, reporterTel, latitude, longitude, city, districts, breedGroup, breed, imgPaths, views, totalLike);
    }

}

