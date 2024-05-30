package inf.saveanimals.repository.posts.lost;

import inf.saveanimals.domain.posts.lost.LostComments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LostCommentsRepository extends JpaRepository<LostComments, Long> {
}
