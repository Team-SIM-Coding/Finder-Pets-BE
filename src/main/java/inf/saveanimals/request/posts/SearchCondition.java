package inf.saveanimals.request.posts;

import inf.saveanimals.domain.animals.common.Breed;
import inf.saveanimals.domain.animals.common.BreedGroup;
import inf.saveanimals.domain.areas.City;
import inf.saveanimals.domain.areas.Districts;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchCondition {

    private String keyword;

    private BreedGroup breedGroup;
    private Breed breed;

    private long viewCount; //조회수
    private long likeCount; //좋아요 수

    private City city;
    private Districts districts;
}
