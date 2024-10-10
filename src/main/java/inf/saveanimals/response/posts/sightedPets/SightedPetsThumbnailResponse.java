package inf.saveanimals.response.posts.sightedPets;

import com.querydsl.core.annotations.QueryProjection;
import inf.saveanimals.domain.animals.common.Breed;
import inf.saveanimals.domain.animals.common.BreedGroup;
import inf.saveanimals.domain.areas.District;
import inf.saveanimals.domain.areas.City;
import inf.saveanimals.domain.areas.District;
import inf.saveanimals.domain.posts.common.Category;
import inf.saveanimals.domain.posts.common.IsCompleted;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class SightedPetsThumbnailResponse {
    private Long pet_id; // sightedPets pk

    private Category category; // 글 카테고리 (실종 / 제보)
    private IsCompleted isCompleted; // 글 상태

    private LocalDateTime date; // 목격한 날짜
    private LocalDateTime created_at; // 작성 시간
    private String area; // 발견된 장소

    private District district;
    private City city;

    private BreedGroup animal; // (동물 종류)
    private Breed kind; // 품종

    private String img_url;  // 이미지

    private Integer views; //조회수
    private Integer total_like; //좋아요 수

    @QueryProjection
    public SightedPetsThumbnailResponse(Long pet_id, Category category, IsCompleted isCompleted, LocalDateTime date, LocalDateTime created_at,
                                        String area, District district, City city, BreedGroup animal, Breed kind,
                                        String img_url, Integer views, Integer total_like) {
        this.pet_id = pet_id;
        this.category = category;
        this.isCompleted = isCompleted;
        this.date = date;
        this.created_at = created_at;
        this.area = area;
        this.district = district;
        this.city = city;
        this.animal = animal;
        this.kind = kind;
        this.img_url = img_url;
        this.views = views;
        this.total_like = total_like;
    }
}
