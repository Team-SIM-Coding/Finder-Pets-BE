package inf.saveanimals.response.posts.sightedPets;

import com.querydsl.core.annotations.QueryProjection;
import inf.saveanimals.domain.animals.common.Breed;
import inf.saveanimals.domain.animals.common.BreedGroup;
import inf.saveanimals.domain.areas.City;
import inf.saveanimals.domain.areas.Districts;
import inf.saveanimals.domain.posts.common.Category;
import inf.saveanimals.domain.posts.common.IsCompleted;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * [제보 포스팅] 단건 조회 반환 데이터
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SightedPetsDetailResponse {

    private Long id; // sightedPets pk

    private String authorNick; // 작성자 닉네임
    private String authorImg; // 작성자 프로필

    private Category category; // 글 카테고리 (실종 / 제보)
    private IsCompleted isCompleted; // 글 상태
    private LocalDateTime foundDate; // 목격한 날짜
    private LocalDateTime createdAt; // 작성 시간
    private String foundPlace; // 발견된 장소
    private String specialMark; // 상세 설명 (내용 입력칸)
    private String reporterTel; // 제보자 연락처

    private String latitude;  // 장소 - 위도
    private String longitude; // 장소 - 경도

    private City city;
    private Districts districts;

    private BreedGroup breedGroup; // (동물 종류)
    private Breed breed; // 품종

    private List<String> imgPaths = new ArrayList<>(); // 이미지 경로 리스트

    private Integer views; //조회수
    private Integer totalLike; //좋아요 수

    @QueryProjection
    @Builder
    public SightedPetsDetailResponse(Long id, String authorNick, String authorImg, Category category, IsCompleted isCompleted, LocalDateTime foundDate, LocalDateTime createdAt,
                                     String foundPlace, String specialMark, String reporterTel,
                                     String latitude, String longitude, City city, Districts districts,
                                     BreedGroup breedGroup, Breed breed, List<String> imgPaths, Integer views, Integer totalLike) {
        this.id = id;
        this.authorNick = authorNick;
        this.authorImg = authorImg;
        this.category = category;
        this.isCompleted = isCompleted;
        this.foundDate = foundDate;
        this.createdAt = createdAt;
        this.foundPlace = foundPlace;
        this.specialMark = specialMark;
        this.reporterTel = reporterTel;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
        this.districts = districts;
        this.breedGroup = breedGroup;
        this.breed = breed;
        this.imgPaths = imgPaths;
        this.views = views;
        this.totalLike = totalLike;
    }
}
