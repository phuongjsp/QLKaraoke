package levantuan.quanlykaraoke.repositories;

import levantuan.quanlykaraoke.entities.VatTu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VatTuRepository extends JpaRepository<VatTu,Long> {

    @Query(nativeQuery = true, value = "select  vt.* from  vat_tu vt " +
            "join chi_tiet_vat_tu ctvt on vt.id = ctvt.vat_tu " +
            "join  phong p on ctvt.phong = p.id " +
            "where p.id = :id")
    List<VatTu> getAllByIdPhong(@Param("id") Long idPhong);

    @Query(value = "select k.ma_vat_tu from vat_tu k order by id desc limit 0, 1", nativeQuery = true)
    String getLastMaPhong();
}
