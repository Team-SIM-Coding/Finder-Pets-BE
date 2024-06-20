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

    public QSightedPetsDetailResponse(com.querydsl.core.types.Expression<Long> pet_id, com.querydsl.core.types.Expression<inf.saveanimals.domain.posts.common.Category> category, com.querydsl.core.types.Expression<inf.saveanimals.domain.posts.common.IsCompleted> isCompleted, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> writerProfileImage, com.querydsl.core.types.Expression<java.time.LocalDateTime> foundDate, com.querydsl.core.types.Expression<java.time.LocalDateTime> createdAt, com.querydsl.core.types.Expression<inf.saveanimals.domain.animals.common.Breed> breed, com.querydsl.core.types.Expression<inf.saveanimals.domain.animals.common.BreedGroup> breedGroup, com.querydsl.core.types.Expression<inf.saveanimals.domain.animals.common.Gender> gender, com.querydsl.core.types.Expression<String> weight, com.querydsl.core.types.Expression<String> color, com.querydsl.core.types.Expression<String> age, com.querydsl.core.types.Expression<inf.saveanimals.domain.animals.common.NeuteringStatus> neuteringStatus, com.querydsl.core.types.Expression<String> specialMark, com.querydsl.core.types.Expression<String> reporterTel, com.querydsl.core.types.Expression<inf.saveanimals.domain.areas.City> city, com.querydsl.core.types.Expression<inf.saveanimals.domain.areas.Districts> districts, com.querydsl.core.types.Expression<String> foundPlace, com.querydsl.core.types.Expression<String> latitude, com.querydsl.core.types.Expression<String> longitude, com.querydsl.core.types.Expression<? extends java.util.List<String>> imgPaths, com.querydsl.core.types.Expression<Integer> views, com.querydsl.core.types.Expression<Integer> totalLike) {
        super(SightedPetsDetailResponse.class, new Class<?>[]{long.class, inf.saveanimals.domain.posts.common.Category.class, inf.saveanimals.domain.posts.common.IsCompleted.class, String.class, String.class, java.time.LocalDateTime.class, java.time.LocalDateTime.class, inf.saveanimals.domain.animals.common.Breed.class, inf.saveanimals.domain.animals.common.BreedGroup.class, inf.saveanimals.domain.animals.common.Gender.class, String.class, String.class, String.class, inf.saveanimals.domain.animals.common.NeuteringStatus.class, String.class, String.class, inf.saveanimals.domain.areas.City.class, inf.saveanimals.domain.areas.Districts.class, String.class, String.class, String.class, java.util.List.class, int.class, int.class}, pet_id, category, isCompleted, name, writerProfileImage, foundDate, createdAt, breed, breedGroup, gender, weight, color, age, neuteringStatus, specialMark, reporterTel, city, districts, foundPlace, latitude, longitude, imgPaths, views, totalLike);
    }

}

