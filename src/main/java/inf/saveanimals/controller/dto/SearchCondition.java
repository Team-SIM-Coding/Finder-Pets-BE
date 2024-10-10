package inf.saveanimals.controller.dto;

import inf.saveanimals.domain.animals.common.Breed;
import inf.saveanimals.domain.animals.common.BreedGroup;
import inf.saveanimals.domain.areas.District;
import inf.saveanimals.domain.areas.City;
import lombok.*;

/**
 * 조회 DTO - 필터 / 검색어? 같은거 담는 곳
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchCondition {

    //private String keyword;

    private BreedGroup kind; // 동물분류
    private Breed animal; // 품종

    //private long viewCount; //조회수
    //private long likeCount; //좋아요 수

    private City city;
    private District district;




}
