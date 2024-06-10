package inf.saveanimals.repository.posts.lost;

import inf.saveanimals.domain.posts.lost.LostImg;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 실종 - 이미지 리포지토리
 */
public interface LostImgRepository extends JpaRepository<LostImg, Long> {
}
