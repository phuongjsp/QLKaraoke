package levantuan.quanlykaraoke.controller;

import levantuan.quanlykaraoke.entities.ChiTietHoaDon;
import levantuan.quanlykaraoke.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class HoaDonResource {

    @Autowired
    HoaDonService hoaDonService;


}
