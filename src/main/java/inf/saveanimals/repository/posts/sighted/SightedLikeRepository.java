package inf.saveanimals.repository.posts.sighted;

import inf.saveanimals.domain.posts.sighted.SightedLike;
import inf.saveanimals.domain.posts.sighted.SightedPets;
import inf.saveanimals.domain.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SightedLikeRepository extends JpaRepository<SightedLike, Long> {
    Optional<SightedLike> findByUserAndSightedPets(User user, SightedPets sightedPets);
}
