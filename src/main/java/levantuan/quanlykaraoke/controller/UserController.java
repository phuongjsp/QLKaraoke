package levantuan.quanlykaraoke.controller;

import levantuan.quanlykaraoke.entities.User;
import levantuan.quanlykaraoke.config.Layout;
import levantuan.quanlykaraoke.service.AuthorityService;
import levantuan.quanlykaraoke.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private AuthorityService authorityService;

    @GetMapping("login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/")
    @Layout(value = "default", title = "trang chu")
    public String indexPage(){
        return "phongHat/thong-ke";
    }
    @Layout(value = "default", title = "Quản lý")
    @GetMapping("/403")
    public String accessDeniedPage() {
        return "403";
    }

    @Layout(value = "default", title = "Đổi mật khẩu")
    @GetMapping("/changePassword")
    public String changePassword(Model model) {
        return "user/changePassword";
    }

    @Layout(value = "default", title = "Đổi mật khẩu")
    @PostMapping("changePassword")
    public String resultChangePassword(Model model, @RequestParam("old-password") String oldPassword,
                                       @RequestParam("new-password") String newpassowrd, Principal principal) {
        System.out.println();
        if (myUserDetailsService.updatePassword(principal.getName(), oldPassword, newpassowrd))
            model.addAttribute("message", "Success Cập nhật mật khẩu thành công !");
        else model.addAttribute("message", "Mật khẩu cũ không chính sác !");
        return "user/changePassword";
    }


    @Layout(value = "default", title = "Quản lý phân quyền")
    @GetMapping("/authorization")
    public String authorization(Model model) {
        return getAuth(model);
    }

    @Layout(value = "default", title = "Quản lý phân quyền")
    @RequestMapping(path = "authorization", method = RequestMethod.POST)
    public String resultAuthorization(Model model, @RequestParam MultiValueMap<String, String> str) {
        List<String> allUser = myUserDetailsService.getAllUsername();
        str.forEach((s, strings) -> allUser.remove(s));
        allUser.forEach(s -> str.put(s, new ArrayList<>()));
        str.forEach((username, roles) -> myUserDetailsService.updateRole(username, roles));
        return getAuth(model);
    }

    private String getAuth(Model model) {
        Map<String, List<String>> authority = new HashMap<>();
        myUserDetailsService.getAllUsername().forEach(username ->
                authority.put(username, myUserDetailsService.getAllRoles(username)));
        model.addAttribute("authority", authority);
        return "user/authorization";
    }

}
