package levantuan.quanlykaraoke.controller;

import levantuan.quanlykaraoke.entities.KhachHang;
import levantuan.quanlykaraoke.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api")
public class KhachHangResource {
    @Autowired
    private KhachHangService khachHangService;

    @GetMapping("khach-hang")
    public Page<KhachHang> getAllKhachHang(@RequestParam Integer pageNumber,
                                       @RequestParam Integer pageSize) {
        return khachHangService.getALlKhachHang(pageNumber -1, pageSize);
    }

    @PostMapping("update-khach-hang")
    @ResponseBody
    public KhachHang updateKhachHang(@RequestBody KhachHang khachHang){
        return khachHangService.updateKhachHang(khachHang);
    }

    @PostMapping("khach-hang")
    @ResponseBody
    public KhachHang newKhachHang(@RequestBody KhachHang khachHang) {
        return khachHangService.newKhachHang(khachHang);
    }


    @GetMapping("xoa-khach-hang/{id}")
    public boolean deleteKhachHang(@PathVariable Long id) {
        return khachHangService.xoaKhachHang(id);
    }



}
/*









}
*/