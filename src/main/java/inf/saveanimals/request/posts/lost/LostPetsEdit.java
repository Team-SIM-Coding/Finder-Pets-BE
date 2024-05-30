package inf.saveanimals.request.posts.lost;

import inf.saveanimals.domain.animals.common.Breed;
import inf.saveanimals.domain.animals.common.Gender;
import inf.saveanimals.domain.animals.common.NeuteringStatus;

import inf.saveanimals.domain.areas.City;
import inf.saveanimals.domain.areas.Districts;
import lombok.*;

/**
 * 실종동물 등록 시 정보 [수정] 입력
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LostPetsEdit {

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

}
