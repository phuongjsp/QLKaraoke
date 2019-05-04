package doan.tuan.quanlykaraoke.repositories;

import doan.tuan.quanlykaraoke.model.LoaiPhongHat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoaiPhongHatRepository extends JpaRepository<LoaiPhongHat, Long> {
    LoaiPhongHat getByTenLoaiPhong(String tenLoaiPhong);

    boolean existsByTenLoaiPhong(String tenLoaiPhong);

    int deleteByTenLoaiPhong(String tenLoaiPhong);
}
