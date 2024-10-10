package inf.saveanimals.request.posts.lost;

import inf.saveanimals.domain.animals.common.Breed;
import inf.saveanimals.domain.animals.common.BreedGroup;
import inf.saveanimals.domain.animals.common.Gender;
import inf.saveanimals.domain.animals.common.NeuteringStatus;

import inf.saveanimals.domain.areas.District;
import inf.saveanimals.domain.areas.City;
import inf.saveanimals.domain.areas.District;
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

    private LocalDateTime date; // 실종 날짜
    private String area; // 잃어버린 장소
    private City city;
    private District district;

    private String latitude; //  잃어버린 장소 - 위도
    private String longitude; // 잃어버린 장소 - 경도

    private BreedGroup animal;
    private Breed kind; // 품종

    private Gender gender; // 성별
    private String weight; // 몸무게
    private String color; //색상
    private String age; // 나이
    private NeuteringStatus is_neutering; // 중성화 여부
    private String character; // 특징
    private String phone; // 보호자 연락처

    private String description; // 상세설명

}
