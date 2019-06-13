package levantuan.quanlykaraoke.repositories;

import levantuan.quanlykaraoke.entities.ChiTietPhieuNhapHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChiTietPhieuNhapHangRepository extends JpaRepository<ChiTietPhieuNhapHang,Long> {
}
