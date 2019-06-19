package levantuan.quanlykaraoke.service.Impl;

import levantuan.quanlykaraoke.entities.ChiTietHoaDon;
import levantuan.quanlykaraoke.entities.HoaDon;
import levantuan.quanlykaraoke.repositories.ChiTietHoaDonRepository;
import levantuan.quanlykaraoke.repositories.HoaDonRepository;
import levantuan.quanlykaraoke.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoaDonServiceImpl implements HoaDonService {
    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private ChiTietHoaDonRepository chiTietHoaDonRepository;

    @Override
    public HoaDon getHoaDonByPhongAndStatus(Long idPhong, Integer status) {
        return hoaDonRepository.getHoaDonByIdPhongAndStatus(idPhong, status);
    }

    @Override
    public List<ChiTietHoaDon> getChiTietHoaDon(Long hoaDonId) {
        return chiTietHoaDonRepository.findAllByHoaDon(hoaDonId.intValue());
    }
}
