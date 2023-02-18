package web.seminar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.seminar.controller.dto.PostRequestDto;
import web.seminar.controller.dto.PostResponseDto;
import web.seminar.controller.dto.PostUpdateRequestDto;
import web.seminar.domain.entity.Post;
import web.seminar.domain.entity.User;
import web.seminar.domain.repository.PostRepository;

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

    @Transactional
    public PostResponseDto findByPostId(Long id) {
        Post post = postRepository.findById(id).orElseThrow
                (() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));;
        return new PostResponseDto(post);
    }

    @Transactional
    public List<PostResponseDto> findAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> new PostResponseDto(post))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        postRepository.delete(post);
    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostUpdateRequestDto postUpdateRequestDto) {
        Post post = postRepository.findById(id).orElseThrow
                (() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        post.update(postUpdateRequestDto.getTitle(), postUpdateRequestDto.getContent());

        return new PostResponseDto(post);
    }
}
