package securitylogin.securitylogin.entity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import securitylogin.securitylogin.service.CustomUserDetailsService;

@Service
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 사용자가 입력한 이메일과 비밀번호를 가져옵니다.
        String userEmail = authentication.getName();
        String userPassword = (String) authentication.getCredentials();

        // 사용자의 상세 정보를 불러옵니다.
        UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

        // 비밀번호를 비교합니다.
        if (!passwordEncoder.matches(userPassword, userDetails.getPassword())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        // 인증 정보를 반환합니다.
        return new UsernamePasswordAuthenticationToken(userEmail, userPassword, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
