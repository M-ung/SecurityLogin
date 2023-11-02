package securitylogin.securitylogin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import securitylogin.securitylogin.dto.UserDto;
import securitylogin.securitylogin.service.UserService;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute UserDto userDto) {
        userService.save(userDto);
        return "redirect:/login";
    }
}
