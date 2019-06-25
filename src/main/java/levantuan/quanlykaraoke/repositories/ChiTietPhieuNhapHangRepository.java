package levantuan.quanlykaraoke.repositories;

import levantuan.quanlykaraoke.entities.ChiTietPhieuNhapHang;
import levantuan.quanlykaraoke.entities.PhieuNhapHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChiTietPhieuNhapHangRepository extends JpaRepository<ChiTietPhieuNhapHang,Long> {

    List<ChiTietPhieuNhapHang> findAllByPhieuNhapHang(PhieuNhapHang phieuNhapHang);
}
