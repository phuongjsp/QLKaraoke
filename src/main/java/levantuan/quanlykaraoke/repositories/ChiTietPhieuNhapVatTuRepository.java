package levantuan.quanlykaraoke.repositories;

import levantuan.quanlykaraoke.entities.ChiTietPhieuNhapVatTu;
import levantuan.quanlykaraoke.entities.PhieuNhapHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChiTietPhieuNhapVatTuRepository  extends JpaRepository<ChiTietPhieuNhapVatTu,Long> {

    List<ChiTietPhieuNhapVatTu> findAllByPhieuNhapHang(PhieuNhapHang phieuNhapHang);
}
