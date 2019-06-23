package levantuan.quanlykaraoke.service.Impl;

import levantuan.quanlykaraoke.dto.HoaDonDTO;
import levantuan.quanlykaraoke.entities.ChiTietHoaDon;
import levantuan.quanlykaraoke.entities.DichVu;
import levantuan.quanlykaraoke.entities.HoaDon;
import levantuan.quanlykaraoke.entities.Phong;
import levantuan.quanlykaraoke.repositories.ChiTietHoaDonRepository;
import levantuan.quanlykaraoke.repositories.DichVuRepository;
import levantuan.quanlykaraoke.repositories.HoaDonRepository;
import levantuan.quanlykaraoke.repositories.PhongRepository;
import levantuan.quanlykaraoke.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HoaDonServiceImpl implements HoaDonService {
    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private PhongRepository phongRepository;

    @Autowired
    private ChiTietHoaDonRepository chiTietHoaDonRepository;

    @Autowired
    private DichVuRepository dichVuRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public HoaDon getbyId(Long id) {
        return hoaDonRepository.findById(id).orElse(null);
    }

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
    public void raPhong(Long id, Integer tienPhong, Integer tienDichVu) {
        HoaDon hoaDon = hoaDonRepository.getOne(id);
        hoaDon.setTinhTrangHoaDon(3);
        Phong phong = phongRepository.getOne(hoaDon.getPhong().getId());
        phong.setTinhTrangPhong(3);
        phongRepository.save(phong);
        hoaDon.setGioRa(new Date());
        hoaDon.setTienPhong(tienPhong);
        hoaDon.setTienDichVu(tienDichVu);
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

    @Override
    public void themDichVu(Long idHoaDon, Long idDichVu, Integer sl) {
        if (chiTietHoaDonRepository.findByIdHoaDonANdIdDichVu(idHoaDon, idDichVu) > 0) {
            chiTietHoaDonRepository.updateNow(idHoaDon, idDichVu, sl);
        } else {
            chiTietHoaDonRepository.insertNow(idHoaDon, idDichVu, sl);
        }
        DichVu dichVu = dichVuRepository.getOne(idDichVu);
        dichVu.setSoLuongCon(dichVu.getSoLuongCon() - sl);
        dichVuRepository.save(dichVu);

    }

    @Override
    public void traDichVu(Long idHoaDon, Long idDichVu, Integer sl) {
        chiTietHoaDonRepository.updateNow(idHoaDon, idDichVu, 0 - sl);
        DichVu dichVu = dichVuRepository.getOne(idDichVu);
        dichVu.setSoLuongCon(dichVu.getSoLuongCon() + sl);
        dichVuRepository.save(dichVu);
    }

    @Override
    public Page<HoaDon> thongKeHoaDon(int page, Integer pageSize, String fromDate, String toDate, Integer typePhong, Long idPhong) {
        String sql = "from  hoa_don a " +
                "left join khach_hang kh on a.khach_hang = kh.id " +
                "left join phong p on a.phong = p.id " +
                "left join loai_phong lp on p.loai_phong = lp.Id where a.tinh_trang_phong = 3 ";

        if (fromDate != null && !fromDate.equals("")) {
            sql += "and str_to_date('" + fromDate + "', '%d-%m-%Y-%H-%i') <= a.thoi_gian_tao ";
        }

        if (toDate != null && !toDate.equals("")) {
            sql += "and str_to_date('" + toDate + "', '%d-%m-%Y-%H-%i') >= a.thoi_gian_tao ";
        }

        if (typePhong != null && typePhong != 0) {
            sql += "and p.loai_phong = " + typePhong + " ";
        }

        if (idPhong != null && idPhong != 0) {
            sql += " and p.id = " + idPhong + " ";
        }
        BigInteger total = (BigInteger) entityManager.createNativeQuery("select count(1) " + sql).getSingleResult();
        sql += " limit " + page + ", " + pageSize;
        Query query = entityManager.createNativeQuery("select a.* " +sql, HoaDon.class);
        return new PageImpl<>(query.getResultList(), PageRequest.of(page, pageSize), total.intValue());
    }

}
