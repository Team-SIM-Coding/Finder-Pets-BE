package inf.saveanimals.repository.posts.sighted;


import inf.saveanimals.request.posts.SearchCondition;
import inf.saveanimals.response.posts.lostPets.LostPetsThumbnailResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchSightedPetsRepository {
    Page<LostPetsThumbnailResponse> findAllBySearchCondition(SearchCondition condition, Pageable pageable);

    Page<LostPetsThumbnailResponse> findAllByAccount(Long userId, Pageable pageable);
}
