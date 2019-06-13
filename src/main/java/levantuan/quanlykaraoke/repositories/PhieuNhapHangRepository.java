package levantuan.quanlykaraoke.repositories;

import levantuan.quanlykaraoke.entities.PhieuNhapHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhieuNhapHangRepository extends JpaRepository<PhieuNhapHang,Long> {
}
