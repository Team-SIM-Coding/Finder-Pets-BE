package inf.saveanimals.repository.posts.sighted;

import inf.saveanimals.domain.posts.sighted.SightedLike;
import inf.saveanimals.domain.posts.sighted.SightedPets;
import inf.saveanimals.domain.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 제보 - 관심하트 리포지토리
 */
public interface SightedLikeRepository extends JpaRepository<SightedLike, Long> {
    Optional<SightedLike> findByUserAndSightedPets(User user, SightedPets sightedPets);
}
