package org.security.kelurahanacademy.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("home")
    public String home() {
        return "pages/home/index";
    }

    @GetMapping("test")
    public String error() {
        return "pages/auth/error-404";
    }
}
