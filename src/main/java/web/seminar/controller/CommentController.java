package web.seminar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.seminar.controller.dto.CommentResponseDTO;
import web.seminar.domain.entity.Comment;
import web.seminar.service.CommentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{postId}")
    public ResponseEntity<?> CommentList(@PathVariable Long postId){
        List<CommentResponseDTO> commentResponseDTOList = commentService.findComments(postId);
    }
}
