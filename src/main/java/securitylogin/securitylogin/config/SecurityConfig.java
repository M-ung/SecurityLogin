package securitylogin.securitylogin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import securitylogin.securitylogin.entity.CustomAuthenticationProvider;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 사용자 정의 인증 프로바이더를 등록합니다.
                .authenticationProvider(authenticationProvider())
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                // 회원가입과 로그인 페이지는 인증 없이 접근 가능하도록 설정합니다.
                                .antMatchers("/signup", "/login").permitAll()
                                // 그 외의 모든 요청은 인증이 필요합니다.
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                // 로그인 페이지의 URL을 설정합니다.
                                .loginPage("/login")
                                // 로그인 성공 시 리다이렉트할 URL을 설정합니다.
                                .defaultSuccessUrl("/main")
                                // 로그인 성공 핸들러를 설정합니다.
                                .successHandler(successHandler())
                                // 로그인 페이지는 인증 없이 접근 가능하도록 설정합니다.
                                .permitAll()
                )
                .logout(logout ->
                        // 로그아웃은 인증 없이 접근 가능하도록 설정합니다.
                        logout.permitAll()
                );
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new SimpleUrlAuthenticationSuccessHandler("/main");
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider();
    }
}
