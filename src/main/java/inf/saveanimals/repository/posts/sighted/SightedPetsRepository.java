package inf.saveanimals.repository.posts.sighted;

import inf.saveanimals.domain.posts.sighted.SightedPets;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 제보 - 본문 리포지토리
 */
public interface SightedPetsRepository extends JpaRepository<SightedPets, Long>, SearchSightedPetsRepository {
}
