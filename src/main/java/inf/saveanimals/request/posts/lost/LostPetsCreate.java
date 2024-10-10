package inf.saveanimals.request.posts.lost;

import inf.saveanimals.domain.animals.common.Breed;
import inf.saveanimals.domain.animals.common.BreedGroup;
import inf.saveanimals.domain.animals.common.Gender;
import inf.saveanimals.domain.animals.common.NeuteringStatus;
import inf.saveanimals.domain.areas.District;
import inf.saveanimals.domain.areas.City;

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

    private LocalDateTime date; // 실종 날짜
    private String area; // 잃어버린 장소
    private City city;
    private District district;

    private String latitude; //  잃어버린 장소 - 위도
    private String longitude; // 잃어버린 장소 - 경도

    private Breed kind; // 품종
    private BreedGroup animal; // 동물분류

    private Gender gender; // 성별
    private String weight; // 몸무게
    private String color; //색상
    private String age; // 나이
    private NeuteringStatus is_neutering; // 중성화 여부

    private String character; // 특징
    private String phone; // 보호자 연락처

    private String description;

    @Builder
    public LostPetsCreate(Breed kind, BreedGroup animal, Gender gender,
                          String weight, String color, String age, NeuteringStatus is_neutering,
                          String character, String phone, String area,
                          District district, City city, LocalDateTime date, String latitude, String longitude, String description) {
        this.kind = kind;
        this.animal = animal;
        this.gender = gender;
        this.weight = weight;
        this.color = color;
        this.age = age;
        this.is_neutering = is_neutering;
        this.character = character;
        this.phone = phone;
        this.area = area;
        this.district = district;
        this.city = city;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
    }


    public LostPets toEntity(User user) {
        return LostPets.builder()
                .breed(kind)
                .breedGroup(animal)
                .gender(gender)
                .weight(weight)
                .color(color)
                .age(age)
                .neuteringStatus(is_neutering)
                .specialMark(character)
                .petOwnerTel(phone)
                .city(city)
                .district(district)
                .lostDate(date)
                .happenPlace(area)
                .latitude(latitude)
                .longitude(longitude)
                .detailed(description)
                .user(user)
                .build();
    }

}
