package inf.saveanimals.repository.posts.lost;

import inf.saveanimals.domain.posts.lost.LostPets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LostPetsRepository extends JpaRepository<LostPets, Long>, SearchLostPetsRepository {
}
