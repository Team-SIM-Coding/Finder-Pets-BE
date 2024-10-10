package inf.saveanimals.repository.posts.lost;


import inf.saveanimals.controller.dto.SearchCondition;
import inf.saveanimals.response.posts.lostPets.LostPetCommentDto;
import inf.saveanimals.response.posts.lostPets.LostPetsThumbnailResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 실종 - 조회용 커스텀 리포지토리
 */
public interface SearchLostPetsRepository {
    Page<LostPetsThumbnailResponse> findAllBySearchCondition(SearchCondition condition, Pageable pageable);
    List<LostPetCommentDto> findCommentByPetId(Long lostPetsId);

}
