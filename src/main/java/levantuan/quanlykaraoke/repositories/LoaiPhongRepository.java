package levantuan.quanlykaraoke.repositories;

import levantuan.quanlykaraoke.entities.LoaiPhong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiPhongRepository extends JpaRepository<LoaiPhong,Long> {
}
