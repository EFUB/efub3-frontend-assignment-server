package web.seminar.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Table(name = "user")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(length = 45)
    private String userName;

    @Column(length = 255)
    private String password;

    @Column(length = 45)
    private String nickname;

    @Builder
    public User(Long userId, String userName, String password, String nickname) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.nickname = nickname;
    }

    public boolean isCorrectPassword(User user){
        return Objects.equals(user.getPassword(), this.password);
    }

}
