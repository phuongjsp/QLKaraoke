package levantuan.quanlykaraoke.repositories;

import levantuan.quanlykaraoke.entities.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon,Long> {

    @Query(nativeQuery = true, value = "select k.ma_hoa_don  from hoa_don k order by id desc limit 0, 1")
    String getLastMaHoaDOn();

    @Query(value = "select * from hoa_don where hoa_don.phong = ?1 and hoa_don.tinh_trang_phong = ?2", nativeQuery = true)
    HoaDon getHoaDonByIdPhongAndStatus(Long idPhong, Integer status);

    @Query(value = "select * from hoa_don where hoa_don.phong = ?1 order by id desc limit 0,1", nativeQuery = true)
    HoaDon getLastHoaDonOfPhong(Long idPhong);


}
