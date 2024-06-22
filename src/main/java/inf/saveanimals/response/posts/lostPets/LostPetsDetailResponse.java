package inf.saveanimals.response.posts.lostPets;

import com.querydsl.core.annotations.QueryProjection;
import inf.saveanimals.domain.animals.common.Breed;
import inf.saveanimals.domain.animals.common.BreedGroup;
import inf.saveanimals.domain.animals.common.Gender;
import inf.saveanimals.domain.animals.common.NeuteringStatus;
import inf.saveanimals.domain.areas.City;
import inf.saveanimals.domain.areas.Districts;
import inf.saveanimals.domain.posts.common.Category;
import inf.saveanimals.domain.posts.common.IsCompleted;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


// 실종 글 - 단건 조회 DTO
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LostPetsDetailResponse {

    private Long pet_id; // lost_pets pk

    private Category category; // 글 카테고리 (실종 / 제보)
    private IsCompleted isCompleted; // 글 상태

   // 작성자 정보
    private String name; // 작성자 닉네임
    private String profile_image; // 작성자 프로필

    // 작성 시간
    private LocalDateTime date; // 실종 날짜
    private LocalDateTime created_at; // 작성 시간

    // 동물 정보
    private Breed kind; // 품종
    private BreedGroup animal; // 동물 종류
    private Gender gender; // 성별
    private String weight; // 몸무게
    private String color; //색상
    private String age; // 나이
    private NeuteringStatus is_neutering; // 중성화 여부
    private String character; // 특징
    private String phone; // 보호자 연락처


    // ---위치 정보---
    private City city;
    private Districts districts;
    private String area; // 잃어버린 장소
    // 지도 api 받고 나서?
    private String latitude; //  잃어버린 장소 - 위도
    private String longitude; // 잃어버린 장소 - 경도

    // 이미지
    private List<String> img_url_list = new ArrayList<>();

    private Integer views; //조회수
    private Integer total_like; //좋아요 수

    private String detailed; // 본문

    @QueryProjection
    @Builder
    public LostPetsDetailResponse(Long pet_id, Category category, IsCompleted isCompleted,
                                  String name, String profile_image,
                                  LocalDateTime date, LocalDateTime created_at,
                                  Breed kind, BreedGroup animal, Gender gender, String weight, String color, String age,
                                  NeuteringStatus is_neutering, String character, String phone,
                                  City city, Districts districts, String area, String latitude, String longitude,
                                  List<String> img_url_list, Integer views, Integer total_like, String detailed) {
        this.pet_id = pet_id;
        this.category = category;
        this.isCompleted = isCompleted;
        this.name = name;
        this.profile_image = profile_image;
        this.date = date;
        this.created_at = created_at;
        this.kind = kind;
        this.animal = animal;
        this.gender = gender;
        this.weight = weight;
        this.color = color;
        this.age = age;
        this.is_neutering = is_neutering;
        this.character = character;
        this.phone = phone;
        this.city = city;
        this.districts = districts;
        this.area = area;
        this.latitude = latitude;
        this.longitude = longitude;
        this.img_url_list = img_url_list;
        this.views = views;
        this.total_like = total_like;
        this.detailed = detailed;
    }
}
