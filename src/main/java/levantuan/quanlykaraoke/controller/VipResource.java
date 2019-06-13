package levantuan.quanlykaraoke.controller;

import levantuan.quanlykaraoke.entities.Vip;
import levantuan.quanlykaraoke.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class VipResource {
    @Autowired
    private VipService vipService;

    @GetMapping("vip")
    public List<Vip> getAll() {
        return  vipService.getAllVip();
    }


    @PostMapping("update-vip")
    @ResponseBody
    public Vip updateVip(@RequestBody Vip vip){
        return vipService.updateVip(vip);
    }

    @PostMapping("vip")
    @ResponseBody
    public Vip newVip(@RequestBody Vip vip) {
        return vipService.newVip(vip);

    }

    @GetMapping("xoa-vip/{id}")
    public boolean deleteVip(@PathVariable Long id) {
        return vipService.xoaVip(id);
    }
}
