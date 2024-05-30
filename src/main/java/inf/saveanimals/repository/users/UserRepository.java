package inf.saveanimals.repository.users;

import inf.saveanimals.domain.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
