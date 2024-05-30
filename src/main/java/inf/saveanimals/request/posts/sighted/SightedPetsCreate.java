package inf.saveanimals.request.posts.sighted;

import inf.saveanimals.domain.animals.common.Breed;
import inf.saveanimals.domain.animals.common.BreedGroup;
import inf.saveanimals.domain.areas.City;
import inf.saveanimals.domain.areas.Districts;
import inf.saveanimals.domain.posts.common.Category;
import inf.saveanimals.domain.posts.common.IsCompleted;
import inf.saveanimals.domain.posts.sighted.SightedPets;
import inf.saveanimals.domain.users.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDateTime;

/**
 * [제보] 동물 등록 시 정보 입력
 */
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SightedPetsCreate {

    private LocalDateTime foundDate; // 목격한 날짜
    private LocalDateTime createdAt; // 작성 시간
    private String foundPlace; // 발견된 장소
    private String specialMark; // 상세 설명 (내용 입력칸)
    private String reporterTel; // 제보자 연락처

    // 지도로 받은 정보
    private String latitude;  // 장소 - 위도
    private String longitude; // 장소 - 경도

    private City city;
    private Districts districts;

    private Breed breed; // 품종

    @Builder
    public SightedPetsCreate(LocalDateTime foundDate, LocalDateTime createdAt,
                             String foundPlace, String specialMark, String reporterTel,
                             String latitude, String longitude, City city, Districts districts, Breed breed) {
        this.foundDate = foundDate;
        this.createdAt = createdAt;
        this.foundPlace = foundPlace;
        this.specialMark = specialMark;
        this.reporterTel = reporterTel;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
        this.districts = districts;
        this.breed = breed;
    }


    public SightedPets toEntity(User user) {
        return SightedPets.builder()
                .user(user)
                .reporterTel(reporterTel)
                .breed(breed)
                .foundDate(foundDate)
                .foundPlace(foundPlace)
                .specialMark(specialMark)
                .city(city)
                .districts(districts)
                .build();
    }


}
