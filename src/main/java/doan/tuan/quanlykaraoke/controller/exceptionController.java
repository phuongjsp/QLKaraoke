package doan.tuan.quanlykaraoke.controller;

import doan.tuan.quanlykaraoke.config.Layout;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class exceptionController {
    @Layout(value = "default", title = "Quản lý")
    @GetMapping("/403")
    public String accessDeniedPage() {
        return "403";
    }
}
