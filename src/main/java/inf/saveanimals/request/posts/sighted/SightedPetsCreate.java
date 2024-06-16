package inf.saveanimals.request.posts.sighted;

import inf.saveanimals.domain.animals.common.Breed;
import inf.saveanimals.domain.animals.common.BreedGroup;
import inf.saveanimals.domain.animals.common.Gender;
import inf.saveanimals.domain.animals.common.NeuteringStatus;
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
 * 제보_본문 - 생성 요청 DTO
 */
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SightedPetsCreate {

    private Breed breed; // 품종
    private BreedGroup breedGroup;

    private Gender gender; // 성별
    private String weight; // 몸무게
    private String color; //색상
    private String age; // 나이
    private NeuteringStatus neuteringStatus; // 중성화 여부

    private String specialMark; // 상세 설명 (내용 입력칸)
    private String reporterTel; // 제보자 연락처
    private String foundPlace; // 발견된 장소

    private City city;
    private Districts districts;

    private LocalDateTime foundDate; // 목격한 날짜

    // 지도로 받은 정보
    private String latitude;  // 장소 - 위도
    private String longitude; // 장소 - 경도

    @Builder
    public SightedPetsCreate(Breed breed, BreedGroup breedGroup, Gender gender,
                             String weight, String color, String age, NeuteringStatus neuteringStatus,
                             String specialMark, String reporterTel, String foundPlace,
                             City city, Districts districts, LocalDateTime foundDate, String latitude, String longitude) {
        this.breed = breed;
        this.breedGroup = breedGroup;
        this.gender = gender;
        this.weight = weight;
        this.color = color;
        this.age = age;
        this.neuteringStatus = neuteringStatus;
        this.specialMark = specialMark;
        this.reporterTel = reporterTel;
        this.foundPlace = foundPlace;
        this.city = city;
        this.districts = districts;
        this.foundDate = foundDate;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public SightedPets toEntity(User user) {
        return SightedPets.builder()
                .breed(breed)
                .breedGroup(breedGroup)
                .gender(gender)
                .weight(weight)
                .color(color)
                .age(age)
                .neuteringStatus(neuteringStatus)
                .specialMark(specialMark)
                .reporterTel(reporterTel)
                .city(city)
                .districts(districts)
                .foundDate(foundDate)
                .foundPlace(foundPlace)
                .latitude(latitude)
                .longitude(longitude)
                .user(user)
                .build();
    }


}
