package levantuan.quanlykaraoke.controller;

import levantuan.quanlykaraoke.config.Layout;
import levantuan.quanlykaraoke.service.PhongHatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PhongHatController {

    @Autowired
    private PhongHatService phongHatService;
    @Layout(value = "default", title = "Phong hat")
    @GetMapping("phong-hat")
    public String list() {
        return "phongHat/danhSach";
    }

    @Layout(value = "default", title = "Phong hat")
    @GetMapping("phong-hat/new")
    public String newPhongHat() {

        return "phongHat/update";
    }

    @Layout(value = "default", title = "Phong hat")
    @GetMapping("phong-hat/update/{id}")
    public String updatePhongHat(@PathVariable Long id,
                                 Model model) {
        model.addAttribute("phong", phongHatService.getById(id));
        return "phongHat/update";
    }

    @Layout(value = "default", title = "Phong hat")
    @GetMapping("vat-tu")
    public String vatTuView() { return "phongHat/vatTu"; }

    @Layout(value = "default", title = "Phong hat")
    @GetMapping("vip")
    public String vipView() {
        return "phongHat/vip";
    }

    @Layout(value = "default", title = "Phong hat")
    @GetMapping("khach-hang")
    public String khachhangView() {
        return "phongHat/khachhang";
    }

    @Layout(value = "default", title = "Phong hat")
    @GetMapping("dich-vu")
    public String dichVuView() { return "phongHat/dichvu"; }

}
