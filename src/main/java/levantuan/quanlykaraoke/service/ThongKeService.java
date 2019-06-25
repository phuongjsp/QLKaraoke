package levantuan.quanlykaraoke.service;

import levantuan.quanlykaraoke.entities.DichVu;

import java.util.List;
import java.util.Map;

public interface ThongKeService {
    Map<Integer, Integer> doanhThuThang(Integer thang, Integer nam);

    Map<Integer, Integer> doanhThuNam(Integer nam);

    // dichVu : thang : sl
    Map<DichVu, Map<Integer, Integer>> dichVuNgay(Integer ngay, Integer thang, Integer nam);
    Map<DichVu, Map<Integer, Integer>> dichVuThang(Integer thang, Integer nam);
    Map<DichVu, Map<Integer, Integer>> dichVuNam(Integer nam);
}
