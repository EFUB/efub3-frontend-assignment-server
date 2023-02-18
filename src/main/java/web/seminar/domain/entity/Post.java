package web.seminar.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "post")
@NoArgsConstructor
public class Post extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id")
    private User author;

    @Column(length = 100)
    private String title;

    @Column()
    private String content;

    @Column(length = 255)
    private String image;

    @Builder
    public Post(Long postId, User author, String title, String content, String image) {
        this.postId = postId;
        this.author = author;
        this.title = title;
        this.content = content;
        this.image = image;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
