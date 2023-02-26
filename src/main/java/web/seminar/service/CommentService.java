package web.seminar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.seminar.controller.dto.CommentResponseDTO;
import web.seminar.domain.entity.Comment;
import web.seminar.domain.repository.CommentRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public List<CommentResponseDTO> findComments(Long postId) {
        return commentRepository.findAllByPostId(postId)
                .stream().map(comment -> new CommentResponseDTO(comment))
                .collect(Collectors.toList());
    }
}

