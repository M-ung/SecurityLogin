package securitylogin.securitylogin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PageController {

    // 메인 페이지로 이동
    @GetMapping("/main")
    public String main() {
        return "main";
    }
}