package web.seminar.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.seminar.domain.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
