package levantuan.quanlykaraoke.repositories;

import levantuan.quanlykaraoke.entities.DichVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DichVuRepository extends JpaRepository<DichVu,Long> {
}
