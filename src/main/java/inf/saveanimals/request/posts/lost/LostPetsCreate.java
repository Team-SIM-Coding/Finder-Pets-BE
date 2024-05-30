package inf.saveanimals.request.posts.lost;

import inf.saveanimals.domain.animals.common.Breed;
import inf.saveanimals.domain.animals.common.Gender;
import inf.saveanimals.domain.animals.common.NeuteringStatus;
import inf.saveanimals.domain.areas.City;
import inf.saveanimals.domain.areas.Districts;

import inf.saveanimals.domain.posts.lost.LostPets;
import inf.saveanimals.domain.users.User;

import lombok.*;

/**
 * 실종동물 등록 시 정보 입력
 */
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LostPetsCreate {

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

    private City city;
    private Districts districts;

    @Builder
    public LostPetsCreate(Breed breed, Gender gender, float weight, String color, String age,
                          NeuteringStatus neuteringStatus, String specialMark, String petOwnerTel, String content,
                          String happenPlace, City city, Districts districts) {
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
        this.city = city;
        this.districts = districts;
    }

    public LostPets toEntity(User user) {
        return LostPets.builder()
                .user(user)
                .breed(breed)
                .gender(gender)
                .weight(weight)
                .color(color)
                .age(age)
                .neuteringStatus(neuteringStatus)
                .specialMark(specialMark)
                .petOwnerTel(petOwnerTel)
                .content(content)
                .happenPlace(happenPlace)
                .city(city)
                .districts(districts)
                .build();
    }

}
