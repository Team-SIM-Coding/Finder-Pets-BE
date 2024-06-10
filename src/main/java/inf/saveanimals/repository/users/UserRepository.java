package inf.saveanimals.repository.users;

import inf.saveanimals.domain.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 회원 - 리포지토리
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
