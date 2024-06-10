package inf.saveanimals.repository.posts.lost;


import inf.saveanimals.request.posts.SearchCondition;
import inf.saveanimals.response.posts.lostPets.LostPetsThumbnailResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 실종 - 조회용 커스텀 리포지토리
 */
public interface SearchLostPetsRepository {
    Page<LostPetsThumbnailResponse> findAllBySearchCondition(SearchCondition condition, Pageable pageable);

    Page<LostPetsThumbnailResponse> findAllByAccount(Long userId, Pageable pageable);
}
