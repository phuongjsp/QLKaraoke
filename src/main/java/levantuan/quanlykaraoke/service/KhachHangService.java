package levantuan.quanlykaraoke.service;

import levantuan.quanlykaraoke.entities.KhachHang;
import levantuan.quanlykaraoke.entities.Phong;
import org.springframework.data.domain.Page;


public interface KhachHangService {
    Page<KhachHang> getALlKhachHang(Integer page, Integer size);

    KhachHang updateKhachHang(KhachHang vatTu);

    KhachHang newKhachHang(KhachHang vatTu);

    boolean xoaKhachHang(Long id);
}
