package web.seminar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.seminar.controller.dto.PostRequestDto;
import web.seminar.controller.dto.PostResponseDto;
import web.seminar.domain.entity.Post;
import web.seminar.domain.entity.User;
import web.seminar.domain.repository.PostRepository;
import web.seminar.exception.UnauthorizedException;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Post createPost(PostRequestDto postRequestDto, User user) {
        return postRepository.save(Post.builder()
                .author(user)
                .title(postRequestDto.getTitle())
                .content(postRequestDto.getContent())
                .build());
    }

    public PostResponseDto findByPostId(Long id) {
        Post post = postRepository.findById(id).orElseThrow
                (() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));;
        return new PostResponseDto(post);
    }

    public List<PostResponseDto> findAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> new PostResponseDto(post))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletePost(Long id, User user) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        if(!user.getUserId().equals(post.getAuthor().getUserId())) {
            throw new UnauthorizedException();
        }
        postRepository.delete(post);
    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto postRequestDto, User user) {
        Post post = postRepository.findById(id).orElseThrow
                (() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        if(!user.getUserId().equals(post.getAuthor().getUserId())) {
            throw new UnauthorizedException();
        }
        post.update(postRequestDto.getTitle(), postRequestDto.getContent());

        return new PostResponseDto(post);
    }
}
