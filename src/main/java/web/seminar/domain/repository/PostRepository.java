package web.seminar.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.seminar.domain.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
