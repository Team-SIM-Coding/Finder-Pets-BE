package inf.saveanimals.repository.posts.sighted;

import inf.saveanimals.domain.posts.sighted.SightedComments;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 제보 - 댓글 리포지토리
 */
public interface SightedCommentsRepository extends JpaRepository<SightedComments, Long> {
}
