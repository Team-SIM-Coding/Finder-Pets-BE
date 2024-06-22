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
    private Long pet_id; // lostPets pk

    private Category category; // 글 카테고리 (실종 / 제보)
    private IsCompleted isCompleted; // 글 상태

    // --실종동물 등록 시 정보 입력--
    private Breed kind; // 품종
    private BreedGroup animal; // 동물 종류

    private Gender gender; // 성별
    private String weight; // 몸무게
    private String character; // 특징
    private String area; // 잃어버린 장소


    // 이미지
    private String img_url;

    private Integer views; //조회수
    private Integer total_like; //좋아요 수

    @QueryProjection
    public LostPetsThumbnailResponse(Long pet_id, Category category, IsCompleted isCompleted,
                                     Breed kind, BreedGroup animal, Gender gender,
                                     String weight, String character, String area,
                                     String img_url, Integer views, Integer total_like) {
        this.pet_id = pet_id;
        this.category = category;
        this.isCompleted = isCompleted;
        this.kind = kind;
        this.animal = animal;
        this.gender = gender;
        this.weight = weight;
        this.character = character;
        this.area = area;
        this.img_url = img_url;
        this.views = views;
        this.total_like = total_like;
    }
}
