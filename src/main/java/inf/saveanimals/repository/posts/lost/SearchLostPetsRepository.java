package inf.saveanimals.repository.posts.lost;


import inf.saveanimals.request.posts.SearchCondition;
import inf.saveanimals.response.posts.lostPets.LostPetsThumbnailResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchLostPetsRepository {
    Page<LostPetsThumbnailResponse> findAllBySearchCondition(SearchCondition condition, Pageable pageable);

    Page<LostPetsThumbnailResponse> findAllByAccount(Long userId, Pageable pageable);
}
