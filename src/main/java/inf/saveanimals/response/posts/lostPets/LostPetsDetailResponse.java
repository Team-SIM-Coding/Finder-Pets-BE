package inf.saveanimals.response.posts.lostPets;

import inf.saveanimals.domain.posts.lost.LostPets;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;


// 실종 글 - 단건 조회 DTO
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LostPetsDetailResponse {

    private Long pet_id; // lost_pets pk

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
    public LostPetsDetailResponse(String user_nickname, String user_name, Long pet_id, int like_count,
                                  String user_profile_image, int view_count, LocalDateTime createdAt, LocalDateTime date,
                                  String city, String district, String area, String latitude, String animal, String longitude,
                                  String kind, String weight, String gender, String color, String age, boolean is_neutering, String charater, String description,
                                  boolean is_completed, List<String> pet_image, String phone) {
        this.user_nickname = user_nickname;
        this.user_name = user_name;
        this.pet_id = pet_id;
        this.like_count = like_count;
        this.user_profile_image = user_profile_image;
        this.view_count = view_count;
        this.createdAt = createdAt;
        this.date = date;
        this.city = city;
        this.district = district;
        this.area = area;
        this.latitude = latitude;
        this.animal = animal;
        this.longitude = longitude;
        this.kind = kind;
        this.weight = weight;
        this.gender = gender;
        this.color = color;
        this.age = age;
        this.is_neutering = is_neutering;
        this.charater = charater;
        this.description = description;
        this.is_completed = is_completed;
        this.pet_image = pet_image;
        this.phone = phone;
    }

    public static LostPetsDetailResponse fromEntity(LostPets lostPets) {
        return LostPetsDetailResponse.builder()
                .pet_id(lostPets.getId())
                .user_name(lostPets.getUser().getName())
                .user_profile_image(lostPets.getUser().getImg())
                .like_count(lostPets.getTotalLike())
                .view_count(lostPets.getViews())
                .date(lostPets.getLostDate())
                .createdAt(lostPets.getCreatedAt())
                .area(lostPets.getHappenPlace())
                .city(lostPets.getCity().getCityName())
                .district(lostPets.getDistrict().getKCode())
                .latitude(lostPets.getLatitude())
                .longitude(lostPets.getLongitude())
                .animal(lostPets.getBreedGroup().getBreed())
                .kind(lostPets.getBreed().getKCode())
                .gender(lostPets.getGender().getKCode())
                .weight(lostPets.getWeight())
                .color(lostPets.getColor())
                .age(lostPets.getAge())
                .is_neutering(lostPets.getNeuteringStatus().is_neutering())
                .charater(lostPets.getSpecialMark())
                .phone(lostPets.getPetOwnerTel())
                .description(lostPets.getDetailed())
                .pet_image(lostPets.getFullImg())
                .is_completed(lostPets.getIsCompleted().is_completed())
                .build();
    }

}
