package web.seminar.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "heart")
@NoArgsConstructor
public class Heart extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long heartId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "post_id")
    private Post post;

    @Column
    private Boolean status;

    @Builder
    public Heart(Long heartId, User user, Post post, Boolean status) {
        this.heartId = heartId;
        this.user = user;
        this.post = post;
        this.status = status;
    }
}
