package levantuan.quanlykaraoke.controller;

import levantuan.quanlykaraoke.config.Layout;
import levantuan.quanlykaraoke.entities.HoaDon;
import levantuan.quanlykaraoke.entities.Phong;
import levantuan.quanlykaraoke.service.HoaDonService;
import levantuan.quanlykaraoke.service.NhapHangSerive;
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

        @Autowired
        private HoaDonService hoaDonService;
        @Autowired
        private NhapHangSerive nhapHangSerive;

    @Layout(value = "default", title = "Phong hat")
    @GetMapping("phong-hat")
    public String list() {
        return "phongHat/danhSach";
    }

    @Layout(value = "default", title = "Phong hat")
    @GetMapping("phong-hat/new")
    public String newPhongHat(Model model) {
        model.addAttribute("phong", new Phong());
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
    public String dichVuView() {
        return "phongHat/dichvu";
    }


    @Layout(value = "default", title = "Phong hat")
    @GetMapping("dat-phong/{id}")
    public String datPhong(@PathVariable Long id,
                           Model model) {
        model.addAttribute("phong", phongHatService.getById(id));
        return "phongHat/dat-phong";
    }

    @Layout(value = "default", title = "Phong hat")
    @GetMapping("hoa-don/{id}")
    public String showHoaDonPhong(@PathVariable Long id,
                                  Model model) {
        HoaDon hoaDon = hoaDonService.getLastHoaDonOfPhong(id);
        model.addAttribute("hoadon", hoaDon);
        model.addAttribute("chiTietHoaDon", hoaDonService.getChiTietHoaDon(hoaDon.getId()));
        return "phongHat/hoa-don";
    }

    @Layout(value = "default", title = "Phong hat")
    @GetMapping("show-hoa-don/{id}")
    public String showHoaDon(@PathVariable Long id,
                                  Model model) {
        HoaDon hoaDon = hoaDonService.getbyId(id);
        model.addAttribute("hoadon", hoaDon);
        model.addAttribute("chiTietHoaDon", hoaDonService.getChiTietHoaDon(hoaDon.getId()));
        return "phongHat/hoa-don";
    }

    @Layout(value = "default", title = "Phong hat")
    @GetMapping("nhap-hang")
    public String nhapHang() {
        return "nhap-hang";
    }

    @Layout(value = "default", title = "Phong hat")
    @GetMapping("nhap-hang/{id}")
    public String hoaDonNhapHang(@PathVariable Long id, Model model) {
        model.addAttribute("hoadon", nhapHangSerive.getById(id));
        model.addAttribute("chitiet", nhapHangSerive.getChiTiet(id));
        return "hoa-don-nhap-hang";
    }
}
