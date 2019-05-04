package doan.tuan.quanlykaraoke.controller;

import doan.tuan.quanlykaraoke.config.Layout;
import doan.tuan.quanlykaraoke.services.PhongHatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("phonghat")
public class PhongHatController {
    private final PhongHatService phongHatService;

    @GetMapping("")
    @Layout(value = "default",title = "Quản lý phòng hát")
    public String getAll(Model model){

        model.addAttribute("phongHat",phongHatService.getAll());
        model.addAttribute("loaiPhongHat",phongHatService.getAllLoaiPhongHat());
        return "phonghat";
    }
}
