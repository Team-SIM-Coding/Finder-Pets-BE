package inf.saveanimals.request.posts.sighted;

import inf.saveanimals.domain.animals.common.Breed;
import inf.saveanimals.domain.animals.common.BreedGroup;
import inf.saveanimals.domain.animals.common.Gender;
import inf.saveanimals.domain.animals.common.NeuteringStatus;
import inf.saveanimals.domain.areas.City;
import inf.saveanimals.domain.areas.Districts;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 제보_본문 - 생성 요청 DTO
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SightedPetsEdit {

    private Breed breed; // 품종
    private BreedGroup breedGroup;

    private Gender gender; // 성별
    private String weight; // 몸무게
    private String color; //색상
    private String age; // 나이
    private NeuteringStatus neuteringStatus; // 중성화 여부

    private String specialMark; // 특징
    private String reporterTel; // 목격자 연락처
    private String foundPlace; // 발견 장소
    private LocalDateTime foundDate; // 발견 날짜

    private City city;
    private Districts districts;
    private String latitude; //  잃어버린 장소 - 위도
    private String longitude; // 잃어버린 장소 - 경도

}