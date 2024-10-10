package inf.saveanimals.repository.posts.lost;

import inf.saveanimals.domain.posts.lost.LostLike;
import inf.saveanimals.domain.posts.lost.LostPets;
import inf.saveanimals.domain.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * 실종 - 관심하트 리포지토리
 */
public interface LostLikeRepository extends JpaRepository<LostLike, Long> {
    Optional<LostLike> findByUserAndLostPets(User user, LostPets lostPets);

    boolean existsByUserAndLostPets(User user, LostPets lostPets);
}
