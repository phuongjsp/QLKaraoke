package levantuan.quanlykaraoke.service;

import levantuan.quanlykaraoke.entities.ChiTietHoaDon;
import levantuan.quanlykaraoke.entities.HoaDon;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HoaDonService {
    HoaDon getLastHoaDonOfPhong(Long idPhong);

    HoaDon getHoaDonByPhongAndStatus(Long idPhong, Integer status);


    List<ChiTietHoaDon> getChiTietHoaDon(Long hoaDonId);

    void huyPhong(Long id);

    void raPhong(Long id);

    void vaoPhong(Long id);
}
