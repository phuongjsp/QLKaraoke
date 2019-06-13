package levantuan.quanlykaraoke.controller;

import levantuan.quanlykaraoke.entities.VatTu;
import levantuan.quanlykaraoke.service.VatTuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class VatTuResource {
    @Autowired
    private VatTuService vatTuService;

    @GetMapping("vat-tu")
    public Page<VatTu> getAll(@RequestParam Integer pageNumber,
                              @RequestParam Integer pageSize) {
        return  vatTuService.getAllVatTu(pageNumber - 1, pageSize);
    }

    @PostMapping("update-vat-tu")
    @ResponseBody
    public VatTu updateVatTu(@RequestBody VatTu vatTu){
        return vatTuService.updateVatTu(vatTu);
    }

    @PostMapping("vat-tu")
    @ResponseBody
    public VatTu newVatTu(@RequestBody VatTu vatTu) {
        return vatTuService.newVatTu(vatTu);
    }

    @GetMapping("xoa-vat-tu/{id}")
    public boolean deleteVatTu(@PathVariable Long id) {
        return vatTuService.xoaVatTu(id);
    }

    @GetMapping("get-vat-tu-by-id-phong")
    public List<VatTu> getALlVatTuByIdPhong(@RequestParam Long idPhong) {
        return vatTuService.getAllVatTuByIdPhong(idPhong);
    }
}

