package levantuan.quanlykaraoke.repositories;

import levantuan.quanlykaraoke.entities.LichSu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LichSuRepository extends JpaRepository<LichSu,Long> {
}
