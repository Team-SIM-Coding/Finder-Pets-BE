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

    private Long id; // lost_pets pk

    private Category category; // 글 카테고리 (실종 / 제보)
    private IsCompleted isCompleted; // 글 상태

   // 작성자 정보
    private String writerNickname; // 작성자 닉네임
    private String writerProfileImage; // 작성자 프로필

    // 작성 시간
    private LocalDateTime lostDate; // 실종 날짜
    private LocalDateTime createdAt; // 작성 시간

    // 동물 정보
    private Breed breed; // 품종
    private BreedGroup breedGroup; // 동물 종류
    private Gender gender; // 성별
    private String weight; // 몸무게
    private String color; //색상
    private String age; // 나이
    private NeuteringStatus neuteringStatus; // 중성화 여부
    private String specialMark; // 특징
    private String petOwnerTel; // 보호자 연락처


    // ---위치 정보---
    private City city;
    private Districts districts;
    private String happenPlace; // 잃어버린 장소
    // 지도 api 받고 나서?
    private String latitude; //  잃어버린 장소 - 위도
    private String longitude; // 잃어버린 장소 - 경도

    // 이미지
    private List<String> imgPaths = new ArrayList<>();

    private Integer views; //조회수
    private Integer totalLike; //좋아요 수

    @QueryProjection
    @Builder
    public LostPetsDetailResponse(Long id, Category category, IsCompleted isCompleted,
                                  String writerNickname, String writerProfileImage,
                                  LocalDateTime lostDate, LocalDateTime createdAt,
                                  Breed breed, BreedGroup breedGroup, Gender gender, String weight, String color, String age,
                                  NeuteringStatus neuteringStatus, String specialMark, String petOwnerTel,
                                  City city, Districts districts, String happenPlace, String latitude, String longitude,
                                  List<String> imgPaths, Integer views, Integer totalLike) {
        this.id = id;
        this.category = category;
        this.isCompleted = isCompleted;
        this.writerNickname = writerNickname;
        this.writerProfileImage = writerProfileImage;
        this.lostDate = lostDate;
        this.createdAt = createdAt;
        this.breed = breed;
        this.breedGroup = breedGroup;
        this.gender = gender;
        this.weight = weight;
        this.color = color;
        this.age = age;
        this.neuteringStatus = neuteringStatus;
        this.specialMark = specialMark;
        this.petOwnerTel = petOwnerTel;
        this.city = city;
        this.districts = districts;
        this.happenPlace = happenPlace;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imgPaths = imgPaths;
        this.views = views;
        this.totalLike = totalLike;
    }
}
