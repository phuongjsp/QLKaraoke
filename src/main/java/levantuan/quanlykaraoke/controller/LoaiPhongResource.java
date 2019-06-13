package levantuan.quanlykaraoke.controller;


import levantuan.quanlykaraoke.entities.LoaiPhong;
import levantuan.quanlykaraoke.service.PhongHatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class LoaiPhongResource {

    @Autowired
    private PhongHatService phongHatService;

    @GetMapping("loai-phong")
    public List<LoaiPhong> getAll() {
        return phongHatService.getAllLoaiPhong();
    }

    @PostMapping("loai-phong")
    @ResponseBody
    public LoaiPhong updateLoaiPhong(@RequestBody LoaiPhong loaiPhong) {
        return phongHatService.newLoaiPhong(loaiPhong);
    }

    @PostMapping("update-loai-phong")
    @ResponseBody
    public LoaiPhong newLoaiPhong(@RequestBody LoaiPhong loaiPhong) {
        return phongHatService.updateLoaiPhong(loaiPhong);
    }

    @GetMapping("xoa-loai-phong/{id}")
    public boolean deleteLoaiPhong(@PathVariable Long id) {
        return phongHatService.xoaLoaiPhong(id);
    }
}
