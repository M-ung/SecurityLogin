package securitylogin.securitylogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import securitylogin.securitylogin.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserEmail(String userEmail);
}