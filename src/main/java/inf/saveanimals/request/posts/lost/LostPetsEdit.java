package inf.saveanimals.request.posts.lost;

import inf.saveanimals.domain.animals.common.Breed;
import inf.saveanimals.domain.animals.common.BreedGroup;
import inf.saveanimals.domain.animals.common.Gender;
import inf.saveanimals.domain.animals.common.NeuteringStatus;

import inf.saveanimals.domain.areas.City;
import inf.saveanimals.domain.areas.Districts;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 실종_본문 - 수정 요청 DTO
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LostPetsEdit {

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
    private LocalDateTime lostDate; // 실종 날짜

    private City city;
    private Districts districts;

    private String latitude; //  잃어버린 장소 - 위도
    private String longitude; // 잃어버린 장소 - 경도

}