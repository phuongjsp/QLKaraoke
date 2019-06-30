package levantuan.quanlykaraoke.service;

import levantuan.quanlykaraoke.dto.NhapHangDTO;
import levantuan.quanlykaraoke.entities.ChiTietPhieuNhapHang;
import levantuan.quanlykaraoke.entities.ChiTietPhieuNhapVatTu;
import levantuan.quanlykaraoke.entities.DichVu;
import levantuan.quanlykaraoke.entities.PhieuNhapHang;

import java.util.List;

public interface NhapHangSerive {

    Long nhapHang(NhapHangDTO nhapHangDTOS, String username);

    PhieuNhapHang getById(Long id);

    List<ChiTietPhieuNhapHang> getChiTiet(Long id);

    List<ChiTietPhieuNhapVatTu> getChiTietVatTu(Long id);

    Long nhapHangVatTu(NhapHangDTO nhapHangDTOS, String username);
}
