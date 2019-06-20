package levantuan.quanlykaraoke.controller;

import levantuan.quanlykaraoke.entities.ChiTietHoaDon;
import levantuan.quanlykaraoke.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class HoaDonResource {

    @Autowired
    HoaDonService hoaDonService;

    @GetMapping("hoa-don/vao-phong/{id}")
    public void vaoPhong(@PathVariable Long id) {
        hoaDonService.vaoPhong(id);
    }

    @GetMapping("hoa-don/ra-phong/{id}")
    public void raPhong(@PathVariable Long id) {
        hoaDonService.raPhong(id);
    }

    @GetMapping("hoa-don/huy-phong/{id}")
    public void huyPhong(@PathVariable Long id) {
        hoaDonService.huyPhong(id);
    }
}
