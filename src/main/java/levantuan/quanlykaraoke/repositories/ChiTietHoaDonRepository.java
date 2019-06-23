package levantuan.quanlykaraoke.repositories;

import levantuan.quanlykaraoke.entities.ChiTietHoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ChiTietHoaDonRepository extends JpaRepository<ChiTietHoaDon, Long> {
    List<ChiTietHoaDon> findAllByHoaDon(Integer idHoaDon);
    @Query(value = "select count(1) from chi_tiet_hoa_don where hoa_don = ?1 and dich_vu = ?2 ", nativeQuery = true)
    int findByIdHoaDonANdIdDichVu(Long idHoaDon, Long idDichVu);
    @Modifying
    @Query(value = "insert into chi_tiet_hoa_don(hoa_don, dich_vu, so_luong_dich_vu) VALUES (?1, ?2, ?3)", nativeQuery = true)
    void insertNow(Long idHoaDon, Long idDichVu, Integer sl);
    @Modifying
    @Query(value = "update chi_tiet_hoa_don set so_luong_dich_vu = so_luong_dich_vu + ?3 where hoa_don = ?1 and dich_vu = ?2", nativeQuery = true)
    void updateNow(Long idHoaDon, Long idDichVu, Integer sl);
}
