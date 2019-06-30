package levantuan.quanlykaraoke.repositories;

import levantuan.quanlykaraoke.entities.NhanVien;
import levantuan.quanlykaraoke.entities.PhieuNhapHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface PhieuNhapHangRepository extends JpaRepository<PhieuNhapHang,Long> {

    Page<PhieuNhapHang> findAllByType(Integer type, Pageable pageable);
    Page<PhieuNhapHang> findAllByNhanVienAndType(NhanVien nhanVien, Integer type, Pageable pageable);

    Page<PhieuNhapHang> findAllByThoiGianNhapLessThanEqualAndType(Date fromDate, Integer type, Pageable pageable);
    Page<PhieuNhapHang> findAllByThoiGianNhapGreaterThanEqualAndType(Date toDate, Integer type, Pageable pageable);
    Page<PhieuNhapHang> findAllByThoiGianNhapLessThanEqualAndThoiGianNhapGreaterThanEqualAndType(Date fromDate, Date toDate, Integer type, Pageable pageable);

    Page<PhieuNhapHang> findAllByThoiGianNhapLessThanEqualAndNhanVienAndType(Date fromDate, NhanVien nhanVien, Integer type, Pageable pageable);
    Page<PhieuNhapHang> findAllByThoiGianNhapGreaterThanEqualAndNhanVienAndType(Date toDate, NhanVien nhanVien, Integer type, Pageable pageable);
    Page<PhieuNhapHang> findAllByThoiGianNhapLessThanEqualAndThoiGianNhapGreaterThanEqualAndNhanVienAndType(Date fromDate, Date toDate, NhanVien nhanVien, Integer type, Pageable pageable);
}
