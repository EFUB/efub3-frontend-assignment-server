package web.seminar.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.seminar.domain.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
}
