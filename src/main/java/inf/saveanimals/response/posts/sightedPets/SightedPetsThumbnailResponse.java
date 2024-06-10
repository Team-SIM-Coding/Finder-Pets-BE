package inf.saveanimals.response.posts.sightedPets;

import com.querydsl.core.annotations.QueryProjection;
import inf.saveanimals.domain.animals.common.Breed;
import inf.saveanimals.domain.animals.common.BreedGroup;
import inf.saveanimals.domain.areas.City;
import inf.saveanimals.domain.areas.Districts;
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
    private Long id; // sightedPets pk

    private Category category; // 글 카테고리 (실종 / 제보)
    private IsCompleted isCompleted; // 글 상태

    private LocalDateTime foundDate; // 목격한 날짜
    private LocalDateTime createdAt; // 작성 시간
    private String foundPlace; // 발견된 장소

    private City city;
    private Districts districts;

    private BreedGroup breedGroup; // (동물 종류)
    private Breed breed; // 품종

    private String thumbnailImgPath;  // 이미지

    private Integer views; //조회수
    private Integer totalLike; //좋아요 수

    @QueryProjection
    public SightedPetsThumbnailResponse(Long id, Category category, IsCompleted isCompleted, LocalDateTime foundDate, LocalDateTime createdAt,
                                        String foundPlace, City city, Districts districts, BreedGroup breedGroup, Breed breed,
                                        String thumbnailImgPath, Integer views, Integer totalLike) {
        this.id = id;
        this.category = category;
        this.isCompleted = isCompleted;
        this.foundDate = foundDate;
        this.createdAt = createdAt;
        this.foundPlace = foundPlace;
        this.city = city;
        this.districts = districts;
        this.breedGroup = breedGroup;
        this.breed = breed;
        this.thumbnailImgPath = thumbnailImgPath;
        this.views = views;
        this.totalLike = totalLike;
    }
}
