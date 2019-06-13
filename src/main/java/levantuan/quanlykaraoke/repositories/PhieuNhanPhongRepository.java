package levantuan.quanlykaraoke.repositories;

import levantuan.quanlykaraoke.entities.PhieuNhanPhong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhieuNhanPhongRepository extends JpaRepository<PhieuNhanPhong,Long> {
}
