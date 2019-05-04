package doan.tuan.quanlykaraoke.controller;

import doan.tuan.quanlykaraoke.config.Layout;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class UserController {
    @GetMapping("/login")
    public String login() {
        return "user/login";
    }


    @GetMapping("/")
    @Layout(value = "default", title = "Hello word")
    public String indexPage() {
        return "index";
    }
}
