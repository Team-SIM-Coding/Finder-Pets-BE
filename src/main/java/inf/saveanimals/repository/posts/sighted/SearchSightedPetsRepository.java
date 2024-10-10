package inf.saveanimals.repository.posts.sighted;


import inf.saveanimals.controller.dto.SearchCondition;

import inf.saveanimals.response.posts.sightedPets.SightedPetsThumbnailResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 제보 조회용 커스텀 리포지토리
 */
public interface SearchSightedPetsRepository {
    Page<SightedPetsThumbnailResponse> findAllBySearchCondition(SearchCondition searchCondition, Pageable pageable);


}
