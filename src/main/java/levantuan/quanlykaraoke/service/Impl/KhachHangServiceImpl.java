package levantuan.quanlykaraoke.service.Impl;

import levantuan.quanlykaraoke.entities.KhachHang;
import levantuan.quanlykaraoke.repositories.KhachHangRepository;
import levantuan.quanlykaraoke.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KhachHangServiceImpl implements KhachHangService {
    @Autowired
    private KhachHangRepository khachHangRepository;


    @Override
    public Page<KhachHang> getALlKhachHang(Integer page, Integer size) {
        return khachHangRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public KhachHang updateKhachHang(KhachHang khachHang) {
        KhachHang kh = khachHangRepository.getOne(khachHang.getId());
        return saveKhachHang(kh, khachHang);
    }

    private KhachHang saveKhachHang(KhachHang kh,KhachHang khachHang) {
        kh.setTenKhachHang(khachHang.getTenKhachHang());
        kh.setSoDienThoai(khachHang.getSoDienThoai());
        kh.setGioiTinh(khachHang.isGioiTinh());
        kh.setCmnd(khachHang.getCmnd());
        return khachHangRepository.save(kh);
    }
    @Override
    public KhachHang newKhachHang(KhachHang khachHang) {
        KhachHang kh = new KhachHang();
        kh.setTenKhachHang(khachHang.getTenKhachHang());
        kh.setSoDienThoai(khachHang.getSoDienThoai());
        kh.setGioiTinh(khachHang.isGioiTinh());
        kh.setCmnd(khachHang.getCmnd());
        khachHangRepository.save(kh);
        kh.setMaKhachhang("KH0"+kh.getId());
        return khachHangRepository.save(kh);
    }

    @Override
    public boolean xoaKhachHang(Long id) {
        try {
            khachHangRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public KhachHang getOne(Long id) {
        return khachHangRepository.getOne(id);
    }
}
