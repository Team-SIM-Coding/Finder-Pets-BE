package inf.saveanimals.response.posts.sightedPets;

import inf.saveanimals.domain.posts.sighted.SightedPets;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class SightedPetsThumbnailResponse {

    private Long pet_id; // sightedPets pk

    private LocalDateTime sightedDate; // 실종 날짜
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
    public SightedPetsThumbnailResponse(Long pet_id, LocalDateTime sightedDate, LocalDateTime createdAt,
                                        String area, String city, String district, String animal, String kind,
                                        String gender, String weight, String color, String age, String character,
                                        String phone, String pet_image, String description, boolean isCompleted,
                                        Integer views, Integer total_like) {
        this.pet_id = pet_id;
        this.sightedDate = sightedDate;
        this.createdAt = createdAt;
        this.area = area;
        this.city = city;
        this.district = district;
        this.animal = animal;
        this.kind = kind;
        this.gender = gender;
        this.weight = weight;
        this.color = color;
        this.age = age;
        this.character = character;
        this.phone = phone;
        this.pet_image = pet_image;
        this.description = description;
        this.isCompleted = isCompleted;
        this.views = views;
        this.total_like = total_like;
    }


    public static SightedPetsThumbnailResponse fromEntity(SightedPets sightedPets) {
        return SightedPetsThumbnailResponse.builder()
                .pet_id(sightedPets.getId())
                .sightedDate(sightedPets.getFoundDate())
                .createdAt(sightedPets.getCreatedAt())
                .area(sightedPets.getFoundPlace())
                .city(sightedPets.getCity().getCityName())
                .district(sightedPets.getDistrict().getKCode())
                .animal(sightedPets.getBreedGroup().getBreed())
                .kind(sightedPets.getBreed().getKCode())
                .gender(sightedPets.getGender().getKCode())
                .weight(sightedPets.getWeight())
                .color(sightedPets.getColor())
                .character(sightedPets.getSpecialMark())
                .phone(sightedPets.getReporterTel())
                .description(sightedPets.getDetailed())
                .pet_image(sightedPets.getThumbnail())
                .isCompleted(sightedPets.getIsCompleted().is_completed())
                .views(sightedPets.getViews())
                .total_like(sightedPets.getTotalLike())
                .build();
    }
}
