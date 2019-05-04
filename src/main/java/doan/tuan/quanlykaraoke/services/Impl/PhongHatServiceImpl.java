package doan.tuan.quanlykaraoke.services.Impl;

import doan.tuan.quanlykaraoke.model.LoaiPhongHat;
import doan.tuan.quanlykaraoke.model.PhongHat;
import doan.tuan.quanlykaraoke.repositories.LoaiPhongHatRepository;
import doan.tuan.quanlykaraoke.repositories.PhongHatRepository;
import doan.tuan.quanlykaraoke.services.PhongHatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class PhongHatServiceImpl implements PhongHatService {
    private final LoaiPhongHatRepository loaiPhongHatRepository;
    private final PhongHatRepository phongHatRepository;
    @Override
    public List<PhongHat> getAll() {
        return phongHatRepository.findAll();
    }

    @Override
    public boolean capNhatTrangThaiPhong(String tenPhong, boolean trangThai) {
        PhongHat phongHat = phongHatRepository.getByTenphong(tenPhong);
        phongHat.setTrangThai(trangThai);
        phongHatRepository.save(phongHat);
        return true;
    }

    @Override
    public List<LoaiPhongHat> getAllLoaiPhongHat() {
        return loaiPhongHatRepository.findAll();
    }

    @Override
    public boolean themPhongHat(String tenPhong, String tenLoaiPhong) {
        if (phongHatRepository.existsByTenphong(tenPhong)) return false;
        PhongHat phongHat = new PhongHat();
        phongHat.setTenphong(tenPhong);
        phongHat.setLoaiPhongHat(loaiPhongHatRepository.getByTenLoaiPhong(tenLoaiPhong));
        phongHat.setTrangThai(false);
        return true;
    }

    @Override
    public boolean xoaPhongHat(String tenPhong) {
        return phongHatRepository.deleteByTenphong(tenPhong)!=0;
    }

    @Override
    public boolean capNhatPhongHat(String tenPhongCu, String tenPhongMoi, String tenLoaiPhong) {
        if (!tenPhongCu.equals(tenPhongMoi))
        if (phongHatRepository.existsByTenphong(tenPhongMoi)) return false;
        PhongHat phongHat = phongHatRepository.getByTenphong(tenPhongCu);
        phongHat.setTenphong(tenPhongMoi);
        phongHat.setLoaiPhongHat(loaiPhongHatRepository.getByTenLoaiPhong(tenLoaiPhong));
      phongHatRepository.save(phongHat);
        return true;
    }

    @Override
    public boolean themLoaiPhongHat(String tenLoaiPhong, long gia) {
        if (loaiPhongHatRepository.existsByTenLoaiPhong(tenLoaiPhong))return false;

        LoaiPhongHat loaiPhongHat = new LoaiPhongHat();
        loaiPhongHat.setTenLoaiPhong(tenLoaiPhong);
        loaiPhongHat.setGiaTien(gia);
        loaiPhongHatRepository.save(loaiPhongHat);
        return true;
    }

    @Override
    public boolean xoaLoaiPhongHat(String tenLoaiPhong) {
        return loaiPhongHatRepository.deleteByTenLoaiPhong(tenLoaiPhong)!=0;
    }

    @Override
    public boolean capNhatLoaiPhongHat(String tenLoaiPhongCu, String tenLoaiPhongMoi, long gia) {
        if (!tenLoaiPhongCu.equals(tenLoaiPhongMoi))
            if (loaiPhongHatRepository.existsByTenLoaiPhong(tenLoaiPhongMoi)) return false;
       LoaiPhongHat loaiPhongHat = loaiPhongHatRepository.getByTenLoaiPhong(tenLoaiPhongCu);
       loaiPhongHat.setTenLoaiPhong(tenLoaiPhongMoi);
       loaiPhongHat.setGiaTien(gia);
       loaiPhongHatRepository.save(loaiPhongHat);
        return true;
    }
}
