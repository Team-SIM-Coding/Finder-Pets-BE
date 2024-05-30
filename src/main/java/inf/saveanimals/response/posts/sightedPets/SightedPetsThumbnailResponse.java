package inf.saveanimals.response.posts.sightedPets;

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

    private long viewCount; //조회수
    private long likeCount; //좋아요 수




}
