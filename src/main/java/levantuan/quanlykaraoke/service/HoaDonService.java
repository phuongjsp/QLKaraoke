package levantuan.quanlykaraoke.service;

import levantuan.quanlykaraoke.entities.ChiTietHoaDon;
import levantuan.quanlykaraoke.entities.HoaDon;

import java.util.List;

public interface HoaDonService {
    HoaDon getHoaDonByPhongAndStatus(Long idPhong, Integer status);

    List<ChiTietHoaDon> getChiTietHoaDon(Long hoaDonId);
}
