package levantuan.quanlykaraoke.service;

import java.util.List;
import java.util.Map;

public interface ThongKeService {
    Map<Integer, Integer> doanhThuThang(Integer thang, Integer nam);

    Map<Integer, Integer> doanhThuNam(Integer nam);

    // dichVu : thang : sl
    Map<Integer, Map<Long, Integer>> dichVuNgay(Integer ngay, Integer thang, Integer nam);
    Map<Integer, Map<Long, Integer>> dichVuThang(Integer thang, Integer nam);
    Map<Integer, Map<Long, Integer>> dichVuNam(Integer nam);
}
