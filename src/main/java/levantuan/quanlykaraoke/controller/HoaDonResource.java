package levantuan.quanlykaraoke.controller;

import levantuan.quanlykaraoke.dto.HoaDonDTO;
import levantuan.quanlykaraoke.entities.ChiTietHoaDon;
import levantuan.quanlykaraoke.entities.HoaDon;
import levantuan.quanlykaraoke.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public void raPhong(@PathVariable Long id,
                        @RequestParam Integer tienPhong,
                        @RequestParam Integer tienDichVu) {
        hoaDonService.raPhong(id, tienPhong, tienDichVu);
    }

    @GetMapping("hoa-don/huy-phong/{id}")
    public void huyPhong(@PathVariable Long id) {
        hoaDonService.huyPhong(id);
    }

    @GetMapping("hoa-don/them-dich-vu/{hoaDon}/{dichVu}/{sl}")
    public void addDichVu(@PathVariable Long hoaDon,
                          @PathVariable Long dichVu,
                          @PathVariable Integer sl) {
        hoaDonService.themDichVu(hoaDon, dichVu, sl);
    }

    @GetMapping("hoa-don/tra-dich-vu/{hoaDon}/{dichVu}/{sl}")
    public void traDichVu(@PathVariable Long hoaDon,
                          @PathVariable Long dichVu,
                          @PathVariable Integer sl) {
        hoaDonService.traDichVu(hoaDon, dichVu, sl);
    }

    @GetMapping("hoa-don")
    public Page<HoaDon> getAllHoaDons(@RequestParam Integer pageNumber,
                                         @RequestParam Integer pageSize,
                                         @RequestParam(required =  false) String fromDate,
                                         @RequestParam(required =  false) String toDate,
                                         @RequestParam(required =  false) Integer typePhong,
                                         @RequestParam(required =  false) Long idPhong) {
        return hoaDonService.thongKeHoaDon(pageNumber - 1, pageSize, fromDate, toDate, typePhong, idPhong);
    }
}
