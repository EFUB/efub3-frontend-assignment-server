package web.seminar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.seminar.domain.entity.Heart;
import web.seminar.domain.entity.Post;
import web.seminar.domain.entity.User;
import web.seminar.domain.repository.HeartRepository;
import web.seminar.domain.repository.PostRepository;

@RequiredArgsConstructor
@Service
public class HeartService {
    private final HeartRepository heartRepository;
    private final PostRepository postRepository;

    @Transactional
    public String likePost(Long id, User user){
        Post post = postRepository.findById(id).orElseThrow
                (() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        if(heartRepository.findByUserAndPost(user, post).isPresent()) {
            return "이미 공감한 글입니다.";
        } else {
            heartRepository.save(Heart.builder()
                    .user(user)
                    .post(post)
                    .status(true)
                    .build());
            return "좋아요";
        }
    }

    @Transactional
    public String deleteLike(Long id, User user){
        Post post = postRepository.findById(id).orElseThrow
                (() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        Heart heart = heartRepository.findByUserAndPost(user, post).orElseThrow
                (() -> new IllegalArgumentException("아직 공감하지 않은 글입니다."));
        heartRepository.delete(heart);
        return "좋아요 취소";
    }

}
