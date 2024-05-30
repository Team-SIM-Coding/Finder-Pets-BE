package inf.saveanimals.repository.posts.sighted;

import inf.saveanimals.domain.posts.sighted.SightedPets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SightedPetsRepository extends JpaRepository<SightedPets, Long> {
}
