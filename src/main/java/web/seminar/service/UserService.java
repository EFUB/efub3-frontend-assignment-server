package web.seminar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.seminar.controller.dto.SignInDTO;
import web.seminar.controller.dto.SignUpDTO;
import web.seminar.domain.entity.User;
import web.seminar.domain.repository.UserRepository;
import web.seminar.exception.UserExistedException;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User addUser(SignUpDTO signUpDTO) {
        Optional<User> user = userRepository.findByUserName(signUpDTO.getUserName());
        if(user.isPresent()){
            throw new UserExistedException();
        }
        return userRepository.save(
                User.builder()
                        .userName(signUpDTO.getUserName())
                        .password(passwordEncoder.encode((signUpDTO.getPassword())))
                        .nickname(signUpDTO.getNickname())
                        .build()
        );
    }

    public String findUser(SignInDTO signInDTO) {

        return "";
    }
}
