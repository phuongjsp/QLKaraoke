package levantuan.quanlykaraoke.service;

import levantuan.quanlykaraoke.entities.DichVu;
import levantuan.quanlykaraoke.entities.PhieuNhapHang;
import org.springframework.data.domain.Page;

import java.util.List;
public interface DichVuService {

    Page<DichVu> getAllDichVu(Integer page, Integer size);

    DichVu updateDichVu(DichVu dichVu);

    DichVu newDichVu(DichVu dichVu);

    boolean xoaDichVu(Long id);

    List<DichVu> danhSachDichVuNho();

    Page<PhieuNhapHang> getAllPhieuNhapHang(String fromDate, String toDate, String nhanVien, Integer type, int page, Integer pageSize);
}

