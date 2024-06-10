package inf.saveanimals.repository.posts.lost;

import inf.saveanimals.domain.posts.lost.LostComments;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 실종-댓글 리포지토리
 */
public interface LostCommentsRepository extends JpaRepository<LostComments, Long> {
}
