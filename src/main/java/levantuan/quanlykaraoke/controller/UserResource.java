package levantuan.quanlykaraoke.controller;

import levantuan.quanlykaraoke.entities.NhanVien;
import levantuan.quanlykaraoke.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class UserResource {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @GetMapping("them-nhan-vien")
    public Long themNhanVien(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String fullname,
                             @RequestParam String address,
                             @RequestParam String sdt,
                             @RequestParam String cmnd,
                             @RequestParam String ngaySinh,
                             @RequestParam String gioiTinh) {
        return myUserDetailsService.newNhanVien(username, password, fullname, address, sdt, cmnd, ngaySinh, gioiTinh);
    }

    @GetMapping("get-nhan-vien/{id}")
    public NhanVien getNhanVien(@PathVariable Long id) {
        return myUserDetailsService.getNhanVien(id);
    }

    @GetMapping("update-nhan-vien/{id}")
    public boolean updateNhanVien (@PathVariable Long id,
                                   @RequestParam(required = false) String password,
                                   @RequestParam(required = false) String fullname,
                                   @RequestParam(required = false) String address,
                                   @RequestParam(required = false) String sdt,
                                   @RequestParam(required = false) String cmnd,
                                   @RequestParam(required = false) String ngaySinh,
                                   @RequestParam(required = false) String gioiTinh) {
        return myUserDetailsService.updateNhanVien(id, password, fullname, address, sdt, cmnd, ngaySinh, gioiTinh);
    }

    @GetMapping("get-nhan-vien")
    public List<String> nhanViens() {
        return myUserDetailsService.getAllUsername();
    }
}
