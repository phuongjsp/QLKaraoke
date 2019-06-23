package levantuan.quanlykaraoke.service.Impl;

import levantuan.quanlykaraoke.entities.DichVu;
import levantuan.quanlykaraoke.repositories.DichVuRepository;
import levantuan.quanlykaraoke.service.ThongKeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ThongKeServiceImpl implements ThongKeService {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private DichVuRepository dichVuRepository;
    @Override
    public Map<Integer, Integer> doanhThuThang(Integer thang, Integer nam) {
        Map<Integer, Integer> map = new HashMap<>();
        int day = 30;
        if (thang == 1 || thang == 3 || thang == 5 || thang == 7 || thang == 8  || thang == 10  || thang == 12) day = 31;
        if (thang == 2) day = 29;
        for (int i = 1; i <= day; i++) {
            map.put(i, selectTongTienByDate(i + "-" + thang + "-" + nam, i + "-" + thang + "-" + nam));
        }
        return map;
    }

    @Override
    public Map<Integer, Integer> doanhThuNam(Integer nam) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= 12; i++) {
            map.put(i, selectTongTienByDate("01-" + i + "-" + nam, "31-" + i + "-" + nam));
        }
        return map;
    }

    @Override
    public Map<Integer, Map<Long, Integer>> dichVuNgay(Integer ngay, Integer thang, Integer nam) {
        Map<Integer, Map<Long, Integer>> map = new HashMap<>();
        List<DichVu> dichVus = dichVuRepository.findAll();
        for (int i = 0; i < 24 ; i++) {
            for (DichVu dichVu : dichVus) {
                Map<Long, Integer> mapDv = new HashMap<>();
                mapDv.put(dichVu.getId(), slDvByDateTime(ngay + "-" + thang + "-" + nam, i));
                map.put(i, mapDv);
            }
        }
        return map;
    }

    @Override
    public Map<Integer, Map<Long, Integer>> dichVuThang(Integer thang, Integer nam) {
        int day = 30;
        if (thang == 1 || thang == 3 || thang == 5 || thang == 7 || thang == 8  || thang == 10  || thang == 12) day = 31;
        if (thang == 2) day = 29;
        Map<Integer, Map<Long, Integer>> map = new HashMap<>();
        List<DichVu> dichVus = dichVuRepository.findAll();
        for (int i = 1; i <= day ; i++) {
            for (DichVu dichVu : dichVus) {
                Map<Long, Integer> mapDv = new HashMap<>();
                mapDv.put(dichVu.getId(), slDvByDate(i + "-" + thang + "-" + nam, i + "-" + thang + "-" + nam));
                map.put(i, mapDv);
            }
        }
        return map;
    }

    @Override
    public Map<Integer, Map<Long, Integer>> dichVuNam(Integer nam) {
        Map<Integer, Map<Long, Integer>> map = new HashMap<>();
        List<DichVu> dichVus = dichVuRepository.findAll();
        for (int i = 1; i <= 12 ; i++) {
            for (DichVu dichVu : dichVus) {
                Map<Long, Integer> mapDv = new HashMap<>();
                mapDv.put(dichVu.getId(), slDvByDate("01-" + i + "-" + nam, "31-" + i + "-" + nam));
                map.put(i, mapDv);
            }
        }
        return map;
    }

    //date dd-MM-yyyy
    private Integer selectTongTienByDate(String date, String date2) {
        String sql = "select IFNULL(sum(a.tien_phong + a.tien_dich_vu), 0 ) " +
                "from hoa_don a " +
                "where a.tinh_trang_phong = 3" +
                "  and" +
                "      a.thoi_gian_tao between str_to_date('" + date +"', '%d-%m-%Y') and str_to_date('" + date2 +" 23:59', '%d-%m-%Y %H:%i')";
        Query query = entityManager.createNativeQuery(sql);
        return ((BigDecimal) query.getSingleResult()).intValue();
    }

    private Integer slDvByDate(String date1, String date2) {
        String sql = "select IFNULL(sum(a.so_luong_dich_vu), 0) " +
                "from chi_tiet_hoa_don a left join dich_vu dv on a.dich_vu = dv.Id " +
                "left join hoa_don hd on a.hoa_don = hd.Id where dich_vu = 2 " +
                "and hd.thoi_gian_tao between str_to_date('" + date1 +"', '%d-%m-%Y') " +
                "and str_to_date('" + date2 +" 23:59', '%d-%m-%Y %H:%i')";
        Query query = entityManager.createNativeQuery(sql);
        return ((BigDecimal) query.getSingleResult()).intValue();
    }

    private Integer slDvByDateTime(String date, Integer time1) {
        String sql = "select IFNULL(sum(a.so_luong_dich_vu), 0) " +
                "from chi_tiet_hoa_don a left join dich_vu dv on a.dich_vu = dv.Id " +
                "left join hoa_don hd on a.hoa_don = hd.Id where dich_vu = 2 " +
                "and hd.thoi_gian_tao between str_to_date('" + date +" " + time1 + ":00', '%d-%m-%Y %H:%i') " +
                "and str_to_date('" + date +" "+ time1 + ":59', '%d-%m-%Y %H:%i')";
        Query query = entityManager.createNativeQuery(sql);
        return ((BigDecimal) query.getSingleResult()).intValue();
    }
}
