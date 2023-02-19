package web.seminar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.seminar.config.authentication.AuthUser;
import web.seminar.controller.dto.PostRequestDto;
import web.seminar.controller.dto.PostResponseDto;
import web.seminar.domain.entity.User;
import web.seminar.service.PostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostRequestDto postRequestDto, @AuthUser User user) {
        postService.createPost(postRequestDto, user);
        return new ResponseEntity<>("게시글이 작성되었습니다.", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public PostResponseDto getPostByPostId(@PathVariable Long id) {
        return postService.findByPostId(id);
    }

    @GetMapping
    public List<PostResponseDto> getAllPosts() {
        return postService.findAllPosts();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id, @AuthUser User user) {
        postService.deletePost(id, user);
        return new ResponseEntity<>("게시글이 삭제되었습니다.", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto, @AuthUser User user) {
        return postService.updatePost(id, postRequestDto, user);
    }

}
