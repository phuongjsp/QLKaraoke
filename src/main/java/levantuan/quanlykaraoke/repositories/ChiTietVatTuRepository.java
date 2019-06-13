package levantuan.quanlykaraoke.repositories;

import levantuan.quanlykaraoke.entities.ChiTietVatTu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ChiTietVatTuRepository extends JpaRepository<ChiTietVatTu,Long> {

    @Query(nativeQuery = true, value = " select * from chi_tiet_vat_tu v where v.phong = ?1 ")
    List<ChiTietVatTu> findAllChiTietVatTuByPhongId(Long id);

    @Modifying
    @Query(nativeQuery = true, value = "update chi_tiet_vat_tu set so_luong_vat_tu = ?1 where id  = ?2")
    Integer updateSlVatTuInPhong(Integer sl, Long idCTVT);

    @Query(nativeQuery = true, value = "select so_luong_vat_tu from chi_tiet_vat_tu where phong = ?1 and vat_tu = ?2")
    Integer slVatTuInPhong(Long idPhong, Long idVatTu);

    @Modifying
    @Query(nativeQuery = true, value = "delete FROM chi_tiet_vat_tu where phong = ?1 and vat_tu = ?2")
    Integer deleteByIdPhongAndIdVatTu(Long idPhong, Long idVattu);


    @Modifying
    @Query(nativeQuery = true, value = "insert into chi_tiet_vat_tu(phong, vat_tu, so_luong_vat_tu) VALUES (?1, ?2, ?3)")
    void insertNewVatTu(Long idPhong, Long idVatTu, Integer sl);
}
