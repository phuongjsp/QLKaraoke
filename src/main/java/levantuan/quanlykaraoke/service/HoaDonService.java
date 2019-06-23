package levantuan.quanlykaraoke.service;

import levantuan.quanlykaraoke.dto.HoaDonDTO;
import levantuan.quanlykaraoke.entities.ChiTietHoaDon;
import levantuan.quanlykaraoke.entities.HoaDon;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HoaDonService {
    HoaDon getbyId(Long id);
    HoaDon getLastHoaDonOfPhong(Long idPhong);

    HoaDon getHoaDonByPhongAndStatus(Long idPhong, Integer status);


    List<ChiTietHoaDon> getChiTietHoaDon(Long hoaDonId);

    void huyPhong(Long id);

    void raPhong(Long id, Integer tienPhong, Integer tienDichVu);

    void vaoPhong(Long id);

    void themDichVu(Long idHoaDon, Long idDichVu, Integer sl);

    void traDichVu(Long idHoaDon, Long idDichVu, Integer sl);

    Page<HoaDon> thongKeHoaDon(int page, Integer pageSize, String fromDate, String toDate, Integer typePhong, Long idPhong);
}
