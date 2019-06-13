package levantuan.quanlykaraoke.controller;

import levantuan.quanlykaraoke.dto.PhongDTO;
import levantuan.quanlykaraoke.dto.UpdatePhongDTO;
import levantuan.quanlykaraoke.entities.Phong;
import levantuan.quanlykaraoke.service.PhongHatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class PhongHatResource {

    @Autowired
    private PhongHatService phongHatService;

    @GetMapping("phong-hat/getAll/{vip}/{status}")
    public Page<Phong> getAllPhong(@PathVariable Integer vip,
                                   @PathVariable Integer status,
                                   @RequestParam Integer pageNumber,
                                   @RequestParam Integer pageSize) {
        return phongHatService.getALlPhong(vip, status, pageNumber -1, pageSize);
    }

    @GetMapping("phong-hat/get/{id}")
    public PhongDTO getPhongById(@PathVariable Long id) {
        return phongHatService.getById(id);
    }

    @PostMapping("phong-hat/get/{id}/{type}")
    public boolean updatePhongById(@PathVariable Long id,
                                   @PathVariable Long type,
                                   @RequestBody List<UpdatePhongDTO> dto) {
        return phongHatService.updatePhong(dto, type, id);
    }
}
