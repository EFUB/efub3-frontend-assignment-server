package web.seminar.controller.dto;

import lombok.Getter;
import web.seminar.domain.entity.Post;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    //작성자 닉네임, 작성 시간, 제목, 이미지, 댓글
    private Long postId;
    private String nickname;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PostResponseDto(Post post) {
        this.postId = post.getPostId();
        this.nickname = post.getAuthor().getNickname();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }
}
