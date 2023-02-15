package web.seminar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.seminar.controller.dto.SignUpDTO;
import web.seminar.domain.entity.User;
import web.seminar.domain.repository.UserRepository;
import web.seminar.exception.UserExistedException;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User addUser(SignUpDTO signUpDTO) {
        User user = userRepository.findByUserName(signUpDTO.getUserName());
        if(user != null){
            throw new UserExistedException();
        }
        user = userRepository.save(
                User.builder()
                        .userName(signUpDTO.getUserName())
                        .password(passwordEncoder.encode((signUpDTO.getPassword())))
                        .nickname(signUpDTO.getNickname())
                        .build()
        );
        return user;
    }
}
