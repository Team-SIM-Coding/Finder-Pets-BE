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

    private Breed kind; // 품종
    private BreedGroup animal;

    private Gender gender; // 성별
    private String weight; // 몸무게
    private String color; //색상
    private String age; // 나이
    private NeuteringStatus is_neutering; // 중성화 여부

    private String character; // 상세 설명 (내용 입력칸)
    private String phone; // 제보자 연락처
    private String area; // 발견된 장소

    private District district;
    private City city;

    private LocalDateTime date; // 목격한 날짜

    // 지도로 받은 정보
    private String latitude;  // 장소 - 위도
    private String longitude; // 장소 - 경도

    private String detailed;

    @Builder
    public SightedPetsCreate(Breed kind, BreedGroup animal, Gender gender,
                             String weight, String color, String age, NeuteringStatus is_neutering,
                             String character, String phone, String area,
                             District district, City city, LocalDateTime date, String latitude, String longitude, String detailed) {
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
        this.detailed = detailed;
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
                .detailed(detailed)
                .user(user)
                .build();
    }


}
