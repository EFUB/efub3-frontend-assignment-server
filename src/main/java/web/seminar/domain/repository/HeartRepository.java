package web.seminar.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.seminar.domain.entity.Heart;
import web.seminar.domain.entity.Post;
import web.seminar.domain.entity.User;

import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    Optional<Heart> findByUserAndPost(User user, Post post);
    Long countByPost(Post post);
}
