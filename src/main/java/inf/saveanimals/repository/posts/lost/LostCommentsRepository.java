package inf.saveanimals.repository.posts.lost;

import inf.saveanimals.domain.posts.lost.LostComments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 실종-댓글 리포지토리
 */
public interface LostCommentsRepository extends JpaRepository<LostComments, Long> {
    List<LostComments> findByLostPetsId(Long lostPetsId);
}
