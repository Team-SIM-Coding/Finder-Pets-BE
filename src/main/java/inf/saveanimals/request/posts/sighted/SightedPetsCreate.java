package inf.saveanimals.request.posts.sighted;

import inf.saveanimals.domain.animals.common.Breed;
import inf.saveanimals.domain.animals.common.BreedGroup;
import inf.saveanimals.domain.animals.common.Gender;
import inf.saveanimals.domain.animals.common.NeuteringStatus;
import inf.saveanimals.domain.areas.District;
import inf.saveanimals.domain.areas.City;
import inf.saveanimals.domain.posts.sighted.SightedPets;
import inf.saveanimals.domain.users.User;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 제보_본문 - 생성 요청 DTO
 */
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SightedPetsCreate {

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
    public SightedPetsCreate(String area, City city, LocalDateTime date, District district, String latitude,
                             Breed kind, BreedGroup animal, String longitude, Gender gender,
                             String color, String age, String weight, NeuteringStatus is_neutering, String character,
                             String phone, String description) {
        this.area = area;
        this.city = city;
        this.date = date;
        this.district = district;
        this.latitude = latitude;
        this.kind = kind;
        this.animal = animal;
        this.longitude = longitude;
        this.gender = gender;
        this.color = color;
        this.age = age;
        this.weight = weight;
        this.is_neutering = is_neutering;
        this.character = character;
        this.phone = phone;
        this.description = description;
    }

    public SightedPets toEntity(User user) {
        return SightedPets.builder()
                .breed(kind)
                .breedGroup(animal)
                .gender(gender)
                .weight(weight)
                .color(color)
                .age(age)
                .neuteringStatus(is_neutering)
                .specialMark(character)
                .reporterTel(phone)
                .city(city)
                .district(district)
                .foundDate(date)
                .foundPlace(area)
                .latitude(latitude)
                .longitude(longitude)
                .detailed(description)
                .user(user)
                .build();
    }

}
