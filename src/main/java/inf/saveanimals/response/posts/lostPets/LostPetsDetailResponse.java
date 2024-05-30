package inf.saveanimals.response.posts.lostPets;

import com.querydsl.core.annotations.QueryProjection;
import inf.saveanimals.domain.animals.common.Breed;
import inf.saveanimals.domain.animals.common.Gender;
import inf.saveanimals.domain.animals.common.NeuteringStatus;
import inf.saveanimals.domain.areas.City;
import inf.saveanimals.domain.areas.Districts;
import inf.saveanimals.domain.posts.common.Category;
import inf.saveanimals.domain.posts.common.IsCompleted;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LostPetsDetailResponse {

    private Long id;
    private Category category; // 글 카테고리 (실종 / 제보)
    private IsCompleted isCompleted; // 글 상태

    // --실종동물 등록 시 정보 입력--
    private Breed breed; // 품종
    private Gender gender; // 성별
    private float weight; // 몸무게
    private String color; //색상
    private String age; // 나이
    private NeuteringStatus neuteringStatus; // 중성화 여부
    private String specialMark; // 특징
    private String petOwnerTel; // 보호자 연락처
    private String content; // 포스팅 본문

    private String happenPlace; // 잃어버린 장소
    // 지도 api 받고 나서?
    private String latitude; //  잃어버린 장소 - 위도
    private String longitude; // 잃어버린 장소 - 경도

    // 이미지
    private List<String> imgPaths = new ArrayList<>();

    private Integer views; //조회수
    private Integer totalLike; //좋아요 수

    // 글쓴이 정보를 가져올 때,-?
    private String authorNick; // 글쓴이 닉네임

    private City city;
    private Districts districts;

    @QueryProjection
    @Builder
    public LostPetsDetailResponse(Long id, Category category, IsCompleted isCompleted, Breed breed, Gender gender,
                                  float weight, String color, String age, NeuteringStatus neuteringStatus, String specialMark,
                                  String petOwnerTel, String content, String happenPlace, String latitude, String longitude,
                                  List<String> imgPaths, Integer views, Integer totalLike,
                                  String authorNick, City city, Districts districts) {
        this.id = id;
        this.category = category;
        this.isCompleted = isCompleted;
        this.breed = breed;
        this.gender = gender;
        this.weight = weight;
        this.color = color;
        this.age = age;
        this.neuteringStatus = neuteringStatus;
        this.specialMark = specialMark;
        this.petOwnerTel = petOwnerTel;
        this.content = content;
        this.happenPlace = happenPlace;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imgPaths = imgPaths;
        this.views = views;
        this.totalLike = totalLike;
        this.authorNick = authorNick;
        this.city = city;
        this.districts = districts;
    }
}
