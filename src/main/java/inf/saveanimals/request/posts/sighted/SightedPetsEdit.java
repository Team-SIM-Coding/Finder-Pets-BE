package inf.saveanimals.request.posts.sighted;

import inf.saveanimals.domain.animals.common.Breed;
import inf.saveanimals.domain.animals.common.Gender;
import inf.saveanimals.domain.animals.common.NeuteringStatus;
import inf.saveanimals.domain.areas.City;
import inf.saveanimals.domain.areas.Districts;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 실종동물 등록 시 정보 [수정] 입력
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SightedPetsEdit {

    private Breed breed; // 품종

    private String specialMark; // 특징
    private String reporterTel; // 목격자 연락처

    private String foundPlace; // 발견 장소
    private LocalDateTime foundDate; // 발견 날짜
    private City city;
    private Districts districts;

}
