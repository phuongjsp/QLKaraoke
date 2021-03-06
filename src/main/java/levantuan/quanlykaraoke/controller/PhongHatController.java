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
    @GetMapping("danh-sach-vat-tu")
    public String vatTuView() {
        return "phongHat/vatTu";
    }

    @Layout(value = "default", title = "Phong hat")
    @GetMapping("vip")
    public String vipView() {
        return "phongHat/vip";
    }

    @Layout(value = "default", title = "Phong hat")
    @GetMapping("danh-sach-khach-hang")
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
    @GetMapping("nhap-hang/{type}/{id}")
    public String hoaDonNhapHang(@PathVariable Integer type,
                                 @PathVariable Long id, Model model) {
        model.addAttribute("type", type);
        model.addAttribute("hoadon", nhapHangSerive.getById(id));
        model.addAttribute("chitiet",type == 0 ? nhapHangSerive.getChiTiet(id) : nhapHangSerive.getChiTietVatTu(id));
        return "hoa-don-nhap-hang";
    }

    @Layout(value = "default", title = "Phong hat")
    @GetMapping("danh-sach-hoa-don")
    public String danhSachHoaDon() {
        return "phongHat/danh-sach-hoa-don";
    }

    @Layout(value = "default", title = "Hóa đơn dich vụ")
    @GetMapping("list-hoa-don/{type}")
    public String hoaDonDichVu(@PathVariable Integer type, Model model) {
        model.addAttribute("type", type);
        return "hoa-don";
    }

    @Layout(value = "default", title = "Phong hat")
    @GetMapping("nhap-vat-tu")
    public String nhapVatTu() {
        return "nhap-hang-vat-tu";
    }

//    @Layout(value = "default", title = "Phong hat")
//    @GetMapping("khach-hang")
//    public String danhSachKH() {
//        return "danh-sach-khach-hang";
//    }

}
