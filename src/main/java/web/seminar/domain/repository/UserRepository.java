package web.seminar.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.seminar.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
