package inf.saveanimals.request.posts.lost;

import inf.saveanimals.domain.animals.common.Breed;
import inf.saveanimals.domain.animals.common.BreedGroup;
import inf.saveanimals.domain.animals.common.Gender;
import inf.saveanimals.domain.animals.common.NeuteringStatus;
import inf.saveanimals.domain.areas.City;
import inf.saveanimals.domain.areas.Districts;

import inf.saveanimals.domain.posts.lost.LostPets;
import inf.saveanimals.domain.users.User;

import lombok.*;

import java.time.LocalDateTime;

/**
 * 실종_본문 - 생성 요청 DTO
 */
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LostPetsCreate {

    private Breed breed; // 품종
    private BreedGroup breedGroup;

    private Gender gender; // 성별

    private String weight; // 몸무게
    private String color; //색상
    private String age; // 나이

    private NeuteringStatus neuteringStatus; // 중성화 여부

    private String specialMark; // 특징
    private String petOwnerTel; // 보호자 연락처
    private String happenPlace; // 잃어버린 장소

    private City city;
    private Districts districts;

    private LocalDateTime lostDate; // 실종 날짜
    private String latitude; //  잃어버린 장소 - 위도
    private String longitude; // 잃어버린 장소 - 경도


    @Builder
    public LostPetsCreate(Breed breed, BreedGroup breedGroup, Gender gender,
                          String weight, String color, String age, NeuteringStatus neuteringStatus,
                          String specialMark, String petOwnerTel, String happenPlace,
                          City city, Districts districts, LocalDateTime lostDate, String latitude, String longitude) {
        this.breed = breed;
        this.breedGroup = breedGroup;
        this.gender = gender;
        this.weight = weight;
        this.color = color;
        this.age = age;
        this.neuteringStatus = neuteringStatus;
        this.specialMark = specialMark;
        this.petOwnerTel = petOwnerTel;
        this.happenPlace = happenPlace;
        this.city = city;
        this.districts = districts;
        this.lostDate = lostDate;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public LostPets toEntity(User user) {
        return LostPets.builder()
                .breed(breed)
                .breedGroup(breedGroup)
                .gender(gender)
                .weight(weight)
                .color(color)
                .age(age)
                .neuteringStatus(neuteringStatus)
                .specialMark(specialMark)
                .petOwnerTel(petOwnerTel)
                .city(city)
                .districts(districts)
                .lostDate(lostDate)
                .happenPlace(happenPlace)
                .latitude(latitude)
                .longitude(longitude)
                .user(user)
                .build();
    }

}
