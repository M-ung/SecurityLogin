package securitylogin.securitylogin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import securitylogin.securitylogin.dto.UserDto;
import securitylogin.securitylogin.entity.User;
import securitylogin.securitylogin.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(UserDto userDto) {
        User user = User.builder()
                .userName(userDto.getUserName())
                .userEmail(userDto.getUserEmail())
                .userPassword(bCryptPasswordEncoder.encode(userDto.getUserPassword()))
                .build();

        System.out.println("회원가입 : " + user);

        Optional<User> byUserEmail = userRepository.findByUserEmail(user.getUserEmail());
        if(!byUserEmail.isPresent())
        {
            userRepository.save(user);
            System.out.println(user.getUserEmail()+"님이 저장되었습니다.");
            userRepository.save(user);
        }
        else {
            System.out.println(user.getUserEmail()+"님이 이미 존재합니다.");
        }
    }
}
