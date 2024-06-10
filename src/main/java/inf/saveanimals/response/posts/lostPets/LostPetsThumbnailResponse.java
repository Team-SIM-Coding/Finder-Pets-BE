package inf.saveanimals.response.posts.lostPets;

import com.querydsl.core.annotations.QueryProjection;
import inf.saveanimals.domain.animals.common.Breed;
import inf.saveanimals.domain.animals.common.BreedGroup;
import inf.saveanimals.domain.animals.common.Gender;
import inf.saveanimals.domain.posts.common.Category;
import inf.saveanimals.domain.posts.common.IsCompleted;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LostPetsThumbnailResponse {
    private Long id; // lostPets pk

    private Category category; // 글 카테고리 (실종 / 제보)
    private IsCompleted isCompleted; // 글 상태

    // --실종동물 등록 시 정보 입력--
    private Breed breed; // 품종
    private BreedGroup breedGroup; // 동물 종류

    private Gender gender; // 성별
    private String weight; // 몸무게
    private String specialMark; // 특징
    private String happenPlace; // 잃어버린 장소


    // 이미지
    private String thumbnailImgPath;

    private Integer views; //조회수
    private Integer totalLike; //좋아요 수

    @QueryProjection
    public LostPetsThumbnailResponse(Long id, Category category, IsCompleted isCompleted,
                                     Breed breed, BreedGroup breedGroup, Gender gender,
                                     String weight, String specialMark, String happenPlace,
                                     String thumbnailImgPath, Integer views, Integer totalLike) {
        this.id = id;
        this.category = category;
        this.isCompleted = isCompleted;
        this.breed = breed;
        this.breedGroup = breedGroup;
        this.gender = gender;
        this.weight = weight;
        this.specialMark = specialMark;
        this.happenPlace = happenPlace;
        this.thumbnailImgPath = thumbnailImgPath;
        this.views = views;
        this.totalLike = totalLike;
    }
}
