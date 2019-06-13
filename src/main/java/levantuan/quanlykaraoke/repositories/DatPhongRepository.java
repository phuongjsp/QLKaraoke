package levantuan.quanlykaraoke.repositories;

import levantuan.quanlykaraoke.entities.DatPhong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatPhongRepository extends JpaRepository<DatPhong,Long> {
}
