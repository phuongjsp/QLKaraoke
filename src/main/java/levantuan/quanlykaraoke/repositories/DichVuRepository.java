package levantuan.quanlykaraoke.repositories;

import levantuan.quanlykaraoke.entities.DichVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DichVuRepository extends JpaRepository<DichVu,Long> {

    @Query("from DichVu where soLuongCon < 10")
    List<DichVu> findAllDichVuSlNho();
}
