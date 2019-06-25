package levantuan.quanlykaraoke.repositories;

import levantuan.quanlykaraoke.entities.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien,Long> {
    NhanVien findByUsername(String username);
}
