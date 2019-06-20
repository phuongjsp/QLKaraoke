package levantuan.quanlykaraoke.service.Impl;

import levantuan.quanlykaraoke.entities.ChiTietHoaDon;
import levantuan.quanlykaraoke.entities.HoaDon;
import levantuan.quanlykaraoke.entities.Phong;
import levantuan.quanlykaraoke.repositories.ChiTietHoaDonRepository;
import levantuan.quanlykaraoke.repositories.HoaDonRepository;
import levantuan.quanlykaraoke.repositories.PhongRepository;
import levantuan.quanlykaraoke.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HoaDonServiceImpl implements HoaDonService {
    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private PhongRepository phongRepository;

    @Autowired
    private ChiTietHoaDonRepository chiTietHoaDonRepository;

    @Override
    public HoaDon getLastHoaDonOfPhong(Long idPhong) {
        return hoaDonRepository.getLastHoaDonOfPhong(idPhong);
    }

    @Override
    public HoaDon getHoaDonByPhongAndStatus(Long idPhong, Integer status) {
        return hoaDonRepository.getHoaDonByIdPhongAndStatus(idPhong, status);
    }

    @Override
    public List<ChiTietHoaDon> getChiTietHoaDon(Long hoaDonId) {
        return chiTietHoaDonRepository.findAllByHoaDon(hoaDonId.intValue());
    }

    @Override
    public void huyPhong(Long id) {
        HoaDon hoaDon = hoaDonRepository.getOne(id);
        hoaDon.setTinhTrangHoaDon(4);
        Phong phong = phongRepository.getOne(hoaDon.getPhong().getId());
        phong.setTinhTrangPhong(1);
        phongRepository.save(phong);
        hoaDonRepository.save(hoaDon);
    }

    @Override
    public void raPhong(Long id) {
        HoaDon hoaDon = hoaDonRepository.getOne(id);
        hoaDon.setTinhTrangHoaDon(3);
        Phong phong = phongRepository.getOne(hoaDon.getPhong().getId());
        phong.setTinhTrangPhong(3);
        phongRepository.save(phong);
        hoaDon.setGioRa(new Date());
        hoaDonRepository.save(hoaDon);
    }

    @Override
    public void vaoPhong(Long id) {
        HoaDon hoaDon = hoaDonRepository.getOne(id);
        hoaDon.setTinhTrangHoaDon(2);
        Phong phong = phongRepository.getOne(hoaDon.getPhong().getId());
        phong.setTinhTrangPhong(4);
        phongRepository.save(phong);
        hoaDon.setGioVao(new Date());
        hoaDonRepository.save(hoaDon);
    }
}
