package web.seminar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.seminar.config.authentication.AuthUser;
import web.seminar.domain.entity.User;
import web.seminar.service.HeartService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hearts")
public class HeartController {
    private  final HeartService heartService;

    @PostMapping("/{id}")
    public ResponseEntity<?> likePost(@PathVariable Long id, @AuthUser User user) {
        String response = heartService.likePost(id, user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelLike(@PathVariable Long id, @AuthUser User user) {
        String response = heartService.deleteLike(id, user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Long getHearts(@PathVariable Long id) {
        return heartService.countHearts(id);
    }

}
