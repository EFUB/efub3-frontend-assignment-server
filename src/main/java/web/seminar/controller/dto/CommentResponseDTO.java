package web.seminar.controller.dto;

import lombok.Builder;
import web.seminar.domain.entity.Comment;

import java.time.LocalDateTime;
import java.util.Date;

public class CommentResponseDTO {
    public Long commentId;
    public String commentAuthorNickname;
    public String commentAuthorId;
    public String contents;
    public LocalDateTime createdAt;

    @Builder
    public CommentResponseDTO(Comment comment) {
        this.commentId = comment.getCommentId();
        this.commentAuthorId = comment.getUser().getUserName();
        this.commentAuthorNickname = comment.getUser().getNickname();
        this.contents = comment.getContent();
        this.createdAt = comment.getCreatedAt();
    }
}
