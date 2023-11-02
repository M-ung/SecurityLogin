package securitylogin.securitylogin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import securitylogin.securitylogin.service.UserService;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;

    @GetMapping("/login")
    public String index() {
        return "login";
    }
}