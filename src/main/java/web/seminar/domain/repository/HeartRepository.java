package web.seminar.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.seminar.domain.entity.Heart;

public interface HeartRepository extends JpaRepository<Heart, Long> {
}
