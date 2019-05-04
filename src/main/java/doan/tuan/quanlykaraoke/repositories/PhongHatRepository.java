package doan.tuan.quanlykaraoke.repositories;

import doan.tuan.quanlykaraoke.model.PhongHat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhongHatRepository extends JpaRepository<PhongHat, Long> {
    boolean existsByTenphong(String tenPhong);
    PhongHat getByTenphong(String tenPhong);

    int deleteByTenphong(String tenPhong);
}
