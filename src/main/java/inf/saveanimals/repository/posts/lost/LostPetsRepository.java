package inf.saveanimals.repository.posts.lost;

import inf.saveanimals.domain.posts.lost.LostPets;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 실종 - 본문 리포지토리
 */
public interface LostPetsRepository extends JpaRepository<LostPets, Long>, SearchLostPetsRepository {
}
