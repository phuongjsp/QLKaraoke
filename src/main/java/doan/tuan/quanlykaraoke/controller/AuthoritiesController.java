package doan.tuan.quanlykaraoke.controller;

import doan.tuan.quanlykaraoke.config.Layout;
import doan.tuan.quanlykaraoke.services.AuthorityService;
import doan.tuan.quanlykaraoke.services.MyUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthoritiesController {
    private final AuthorityService authorityService;
    private final MyUserDetailsService myUserDetailsService;

    @GetMapping()
    @Layout(value = "default", title = "Quản lý phân quyền")
    public String getAll(Model model) {
        Map<String, List<String>> mapAuth = new HashMap<>();
        myUserDetailsService.getAllUsername().forEach(username ->
                mapAuth.put(username, authorityService.getAllAuthoritiesByUsername(username)));
        model.addAttribute("auths",mapAuth);
        return "authorities";
    }
    @PostMapping()
    @Layout(value = "default", title = "Quản lý phân quyền")
    public String resultAuthorities(Model model,
                                    @RequestParam MultiValueMap<String,String> auth){
       List<String> userNonAuth = myUserDetailsService.getAllUsername();
       userNonAuth.removeAll(auth.keySet());
        userNonAuth.forEach(authorityService::deleteAllAuthoritiesOfUsername);
        auth.forEach(authorityService::setAuthoritiesForUser);
        model.addAttribute("messages","Cập nhật thành công");
        return getAll(model);
    }

}
