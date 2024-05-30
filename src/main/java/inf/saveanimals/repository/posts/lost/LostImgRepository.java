package inf.saveanimals.repository.posts.lost;

import inf.saveanimals.domain.posts.lost.LostImg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LostImgRepository extends JpaRepository<LostImg, Long> {
}
