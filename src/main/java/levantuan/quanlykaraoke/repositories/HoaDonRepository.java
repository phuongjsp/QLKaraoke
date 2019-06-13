package levantuan.quanlykaraoke.repositories;

import levantuan.quanlykaraoke.entities.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon,Long> {
}
