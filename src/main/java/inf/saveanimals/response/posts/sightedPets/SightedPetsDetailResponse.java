package inf.saveanimals.response.posts.sightedPets;

import inf.saveanimals.domain.posts.sighted.SightedPets;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


/**
 * [제보 포스팅] 단건 조회 반환 데이터
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SightedPetsDetailResponse {

    private Long pet_id; // sighted_pets pk

    private String user_nickname;
    private String user_name;
    private String user_profile_image;
    private int like_count;
    private int view_count;
    private LocalDateTime date; // 실종 날짜
    private LocalDateTime createdAt; // 작성 시간

    private String area; // 장소 상세설명
    private String city; // 도시
    private String district; // 지역구
    private String latitude;
    private String longitude;

    private String animal;
    private String kind;
    private String gender;
    private String weight;
    private String color;
    private String age;
    private boolean is_neutering;
    private String charater;
    private String phone;
    private String description;
    private List<String> pet_image;

    private boolean is_completed;


    @Builder
    public SightedPetsDetailResponse(Long pet_id, String user_nickname, String user_name, String user_profile_image,
                                     int view_count, LocalDateTime createdAt, int like_count,
                                     LocalDateTime date, String city, String latitude, String district, String area, String longitude,
                                     String animal, String kind, String gender, String weight, String color, String age,
                                     boolean is_neutering, String charater, String description, boolean is_completed, List<String> pet_image, String phone) {
        this.pet_id = pet_id;
        this.user_nickname = user_nickname;
        this.user_name = user_name;
        this.user_profile_image = user_profile_image;
        this.view_count = view_count;
        this.createdAt = createdAt;
        this.like_count = like_count;
        this.date = date;
        this.city = city;
        this.latitude = latitude;
        this.district = district;
        this.area = area;
        this.longitude = longitude;
        this.animal = animal;
        this.kind = kind;
        this.gender = gender;
        this.weight = weight;
        this.color = color;
        this.age = age;
        this.is_neutering = is_neutering;
        this.charater = charater;
        this.description = description;
        this.is_completed = is_completed;
        this.pet_image = pet_image;
        this.phone = phone;
    }


    public static SightedPetsDetailResponse fromEntity(SightedPets sightedPets) {
        return SightedPetsDetailResponse.builder()
                .pet_id(sightedPets.getId())
                .user_name(sightedPets.getUser().getName())
                .user_profile_image(sightedPets.getUser().getImg())
                .like_count(sightedPets.getTotalLike())
                .view_count(sightedPets.getViews())
                .date(sightedPets.getFoundDate())
                .createdAt(sightedPets.getCreatedAt())
                .area(sightedPets.getFoundPlace())
                .city(sightedPets.getCity().getCityName())
                .district(sightedPets.getDistrict().getKCode())
                .latitude(sightedPets.getLatitude())
                .longitude(sightedPets.getLongitude())
                .animal(sightedPets.getBreedGroup().getBreed())
                .kind(sightedPets.getBreed().getKCode())
                .gender(sightedPets.getGender().getKCode())
                .weight(sightedPets.getWeight())
                .color(sightedPets.getColor())
                .age(sightedPets.getAge())
                .is_neutering(sightedPets.getNeuteringStatus().is_neutering())
                .charater(sightedPets.getSpecialMark())
                .phone(sightedPets.getReporterTel())
                .description(sightedPets.getDetailed())
                .pet_image(sightedPets.getFullImg())
                .is_completed(sightedPets.getIsCompleted().is_completed())
                .build();
    }
}
