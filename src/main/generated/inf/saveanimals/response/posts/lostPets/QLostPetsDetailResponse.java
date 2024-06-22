package inf.saveanimals.response.posts.lostPets;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * inf.saveanimals.response.posts.lostPets.QLostPetsDetailResponse is a Querydsl Projection type for LostPetsDetailResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QLostPetsDetailResponse extends ConstructorExpression<LostPetsDetailResponse> {

    private static final long serialVersionUID = 1091752793L;

    public QLostPetsDetailResponse(com.querydsl.core.types.Expression<Long> pet_id, com.querydsl.core.types.Expression<inf.saveanimals.domain.posts.common.Category> category, com.querydsl.core.types.Expression<inf.saveanimals.domain.posts.common.IsCompleted> isCompleted, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> profile_image, com.querydsl.core.types.Expression<java.time.LocalDateTime> date, com.querydsl.core.types.Expression<java.time.LocalDateTime> created_at, com.querydsl.core.types.Expression<inf.saveanimals.domain.animals.common.Breed> kind, com.querydsl.core.types.Expression<inf.saveanimals.domain.animals.common.BreedGroup> animal, com.querydsl.core.types.Expression<inf.saveanimals.domain.animals.common.Gender> gender, com.querydsl.core.types.Expression<String> weight, com.querydsl.core.types.Expression<String> color, com.querydsl.core.types.Expression<String> age, com.querydsl.core.types.Expression<inf.saveanimals.domain.animals.common.NeuteringStatus> is_neutering, com.querydsl.core.types.Expression<String> character, com.querydsl.core.types.Expression<String> phone, com.querydsl.core.types.Expression<inf.saveanimals.domain.areas.City> city, com.querydsl.core.types.Expression<inf.saveanimals.domain.areas.Districts> districts, com.querydsl.core.types.Expression<String> area, com.querydsl.core.types.Expression<String> latitude, com.querydsl.core.types.Expression<String> longitude, com.querydsl.core.types.Expression<? extends java.util.List<String>> img_url_list, com.querydsl.core.types.Expression<Integer> views, com.querydsl.core.types.Expression<Integer> total_like, com.querydsl.core.types.Expression<String> detailed) {
        super(LostPetsDetailResponse.class, new Class<?>[]{long.class, inf.saveanimals.domain.posts.common.Category.class, inf.saveanimals.domain.posts.common.IsCompleted.class, String.class, String.class, java.time.LocalDateTime.class, java.time.LocalDateTime.class, inf.saveanimals.domain.animals.common.Breed.class, inf.saveanimals.domain.animals.common.BreedGroup.class, inf.saveanimals.domain.animals.common.Gender.class, String.class, String.class, String.class, inf.saveanimals.domain.animals.common.NeuteringStatus.class, String.class, String.class, inf.saveanimals.domain.areas.City.class, inf.saveanimals.domain.areas.Districts.class, String.class, String.class, String.class, java.util.List.class, int.class, int.class, String.class}, pet_id, category, isCompleted, name, profile_image, date, created_at, kind, animal, gender, weight, color, age, is_neutering, character, phone, city, districts, area, latitude, longitude, img_url_list, views, total_like, detailed);
    }

}

