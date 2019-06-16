package levantuan.quanlykaraoke.repositories;

import levantuan.quanlykaraoke.entities.LoaiPhong;
import levantuan.quanlykaraoke.entities.Phong;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PhongRepository extends JpaRepository<Phong, Long> {

    @Modifying //chỉ dùng khi update và delete
    @Query(nativeQuery = true, value = "delete from phong where id = :id")
    Integer  deletePhongById(@Param("id") Long id);


    @Query(value = "select p.* from phong p where p.loai_phong = ?1 ", nativeQuery = true)
    Page<Phong> findAllByVipLoaiPhong(Integer vip, Pageable pageable);

    @Query(value = "select p.* from phong p where p.tinh_trang_phong = ?1 ", nativeQuery = true)
    Page<Phong> findAllByStatusPhong(Integer status, Pageable pageable);

    @Query(value = "select p.* from phong p where p.loai_phong = ?1 and p.tinh_trang_phong = ?2 ", nativeQuery = true)
    Page<Phong> findAllByIdAndStatusLoaiPhong(Integer id, Integer status, Pageable pageable);

    @Modifying
    @Query(nativeQuery = true, value = "update phong set loai_phong = ?1 where id = ?2")
    void updateType(Long idType, Long idPhong);
    @Query(nativeQuery = true, value = "select k.ma_phong  from phong k order by id desc limit 0, 1")
    String getLastMaPhong();

    Page<Phong> findAllByLoaiPhong(LoaiPhong loaiPhong, Pageable pageable);
    Page<Phong> findAllByTinhTrangPhong(Integer loaiPhong, Pageable pageable);
}
