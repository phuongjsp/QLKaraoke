package levantuan.quanlykaraoke.repositories;

import levantuan.quanlykaraoke.entities.ChiTietHoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChiTietHoaDonRepository extends JpaRepository<ChiTietHoaDon, Long> {
    List<ChiTietHoaDon> findAllByHoaDon(Integer idHoaDon);
}
