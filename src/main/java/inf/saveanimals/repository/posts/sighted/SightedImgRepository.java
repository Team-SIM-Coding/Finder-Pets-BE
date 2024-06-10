package inf.saveanimals.repository.posts.sighted;

import inf.saveanimals.domain.posts.sighted.SightedImg;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 제보-이미지 리포지토리
 */
public interface SightedImgRepository extends JpaRepository<SightedImg, Long> {
}
