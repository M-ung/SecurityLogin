package securitylogin.securitylogin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import securitylogin.securitylogin.entity.User;
import securitylogin.securitylogin.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 사용자의 이메일로 데이터베이스에서 사용자 정보를 조회합니다.
        Optional<User> userOptional = userRepository.findByUserEmail(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        // Spring Security의 UserDetails 객체를 생성하여 반환합니다.
        return new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getUserPassword(), Collections.emptyList());
    }
}
