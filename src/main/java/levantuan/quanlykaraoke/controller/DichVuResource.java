package levantuan.quanlykaraoke.controller;

import levantuan.quanlykaraoke.entities.DichVu;
import levantuan.quanlykaraoke.service.DichVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class DichVuResource {
    @Autowired
    private DichVuService dichVuService;

    @GetMapping("dich-vu")
    public Page<DichVu> getAllKhachHang(@RequestParam Integer pageNumber,
                                        @RequestParam Integer pageSize) {
        return dichVuService.getAllDichVu(pageNumber -1, pageSize);
    }

    @GetMapping("dich-vu-sap-het")
    public List<DichVu> danhSachDichVuNhoHon10() {
        return dichVuService.danhSachDichVuNho();
    }

    @PostMapping("update-dich-vu")
    @ResponseBody
    public DichVu updateKhachHang(@RequestBody DichVu dichVu){
        return dichVuService.updateDichVu(dichVu);
    }

    @PostMapping("dich-vu")
    @ResponseBody
    public DichVu newKhachHang(@RequestBody DichVu dichVu) {
        return dichVuService.newDichVu(dichVu);
    }


    @GetMapping("xoa-dich-vu/{id}")
    public boolean deleteKhachHang(@PathVariable Long id) {
        return dichVuService.xoaDichVu(id);
    }


//    @Autowired
//    private DichVuService dichVuService;
//
//    @GetMapping("dich-vu")
//    public List<DichVu> getAll() {
//        return dichVuService.getAllDichVu();
//    }
//
//    @PostMapping("update-dich-vu")
//    @ResponseBody
//    public DichVu updateDichVu(@RequestBody DichVu dichVu) {
//        return dichVuService.updateDichVu(dichVu);
//    }
//
//    @PostMapping("dich-vu")
//    @ResponseBody
//    public DichVu newDichVu(@RequestBody DichVu dichVu) {
//        return dichVuService.newDichVu(dichVu);
//    }
//
//    @GetMapping("xoa-dich-vu/{id}")
//    public boolean xoaDichVu(@PathVariable Long id) {
//        return dichVuService.xoaDichVu(id);
//    }

}


