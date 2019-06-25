package levantuan.quanlykaraoke.controller;

import levantuan.quanlykaraoke.config.Layout;
import levantuan.quanlykaraoke.dto.NhapHangDTO;
import levantuan.quanlykaraoke.entities.DichVu;
import levantuan.quanlykaraoke.service.NhapHangSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api")
public class NhapHangResource {

    @Autowired
    private NhapHangSerive nhapHangSerive;

    @PostMapping("nhap-hang")
    public Long nhapHang(Model model, @RequestBody NhapHangDTO dichVus,
                           Principal principal) {

        return nhapHangSerive.nhapHang(dichVus, principal.getName()).getId();
    }
}
