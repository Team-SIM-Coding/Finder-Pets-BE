package inf.saveanimals.response.posts.lostPets;

import com.querydsl.core.annotations.QueryProjection;
import inf.saveanimals.domain.animals.common.Breed;
import inf.saveanimals.domain.animals.common.BreedGroup;
import inf.saveanimals.domain.animals.common.Gender;
import inf.saveanimals.domain.posts.common.Category;
import inf.saveanimals.domain.posts.common.IsCompleted;

import inf.saveanimals.domain.posts.lost.LostPets;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class LostPetsThumbnailResponse {
    private Long pet_id; // lostPets pk

    private LocalDateTime lostDate; // 실종 날짜
    private LocalDateTime createdAt; // 작성 시간

    private String area;
    private String city;
    private String district;

    private String animal; // 동물 종류
    private String kind; // 품종
    private String gender; // 성별
    private String weight; // 몸무게
    private String color;
    private String age;
    private String character; // 특징
    private String phone; // 연락망
    private String description;
    private String pet_image;
    private boolean isCompleted; // 글 상태

    private Integer views; //조회수
    private Integer total_like; //좋아요 수


    @Builder
    public LostPetsThumbnailResponse(Long pet_id, LocalDateTime lostDate, LocalDateTime createdAt, String district,
                                     String weight, String area, String city, String kind, String gender, String animal,
                                     String age, String color, String phone, String character, String description, String pet_image,
                                     boolean isCompleted, Integer views, Integer total_like) {
        this.pet_id = pet_id;
        this.lostDate = lostDate;
        this.createdAt = createdAt;
        this.district = district;
        this.weight = weight;
        this.area = area;
        this.city = city;
        this.kind = kind;
        this.gender = gender;
        this.animal = animal;
        this.age = age;
        this.color = color;
        this.phone = phone;
        this.character = character;
        this.description = description;
        this.pet_image = pet_image;
        this.isCompleted = isCompleted;
        this.views = views;
        this.total_like = total_like;
    }



    public static LostPetsThumbnailResponse fromEntity(LostPets lostPets) {
        return LostPetsThumbnailResponse.builder()
                .pet_id(lostPets.getId())
                .lostDate(lostPets.getLostDate())
                .createdAt(lostPets.getCreatedAt())
                .area(lostPets.getHappenPlace())
                .city(lostPets.getCity().getCityName())
                .district(lostPets.getDistrict().getKCode())
                .animal(lostPets.getBreedGroup().getBreed())
                .kind(lostPets.getBreed().getKCode())
                .gender(lostPets.getGender().getKCode())
                .weight(lostPets.getWeight())
                .color(lostPets.getColor())
                .character(lostPets.getSpecialMark())
                .phone(lostPets.getPetOwnerTel())
                .description(lostPets.getDetailed())
                .pet_image(lostPets.getThumbnail())
                .isCompleted(lostPets.getIsCompleted().is_completed())
                .views(lostPets.getViews())
                .total_like(lostPets.getTotalLike())
                .build();
    }
}
