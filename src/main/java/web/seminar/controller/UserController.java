package web.seminar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.seminar.controller.dto.SignInDTO;
import web.seminar.controller.dto.SignUpDTO;
import web.seminar.domain.entity.User;
import web.seminar.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public ResponseEntity<?> signUp(@RequestBody SignUpDTO signUpDTO){
        User user = userService.addUser(signUpDTO);
        if(user.getUserId() == null) {
            return new ResponseEntity<>("에러가 발생했습니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("회원가입에 성공하였습니다.", HttpStatus.CREATED);
    }

    public ResponseEntity<?> signIn(@RequestBody SignInDTO signInDTO){
        String authToken = userService.findUser(signInDTO);
        return new ResponseEntity<>(authToken, HttpStatus.OK);
    }
}
