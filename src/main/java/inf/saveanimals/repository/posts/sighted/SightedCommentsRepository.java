package inf.saveanimals.repository.posts.sighted;

import inf.saveanimals.domain.posts.sighted.SightedComments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SightedCommentsRepository extends JpaRepository<SightedComments, Long> {
}
