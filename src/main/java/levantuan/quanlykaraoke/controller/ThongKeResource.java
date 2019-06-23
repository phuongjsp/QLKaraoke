package levantuan.quanlykaraoke.controller;

import levantuan.quanlykaraoke.service.ThongKeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api")
public class ThongKeResource {
    @Autowired
    private ThongKeService thongKeService;

    @GetMapping("thong-ke-nam/{nam}")
    public Map<Integer, Integer> thongKeTheoThang(@PathVariable Integer nam) {
        return thongKeService.doanhThuNam(nam);
    }

    @GetMapping("thong-ke-thang/{thang}/{nam}")
    public Map<Integer, Integer> thongKeTheoThang(@PathVariable Integer thang,
                                                  @PathVariable Integer nam) {
        return thongKeService.doanhThuThang(thang, nam);
    }

    @GetMapping("thong-ke-dich-vu-nam/{nam}")
    public Map<Integer, Map<Long, Integer>> thongKeTheoDv(@PathVariable Integer nam) {
        return thongKeService.dichVuNam(nam);
    }

    @GetMapping("thong-ke-dich-vu-thang/{thang}/{nam}")
    public Map<Integer, Map<Long, Integer>> thongKeTheoDv(@PathVariable Integer thang,
                                                          @PathVariable Integer nam) {
        return thongKeService.dichVuThang(thang, nam);
    }

    @GetMapping("thong-ke-dich-vu-ngay/{ngay}/{thang}/{nam}")
    public Map<Integer, Map<Long, Integer>> thongKeTheoDv(@PathVariable Integer ngay,
                                                          @PathVariable Integer thang,
                                                          @PathVariable Integer nam) {
        return thongKeService.dichVuNgay(ngay, thang, nam);
    }
}
