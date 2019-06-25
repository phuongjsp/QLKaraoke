package levantuan.quanlykaraoke.service.Impl;

import levantuan.quanlykaraoke.dto.DichVuDTO;
import levantuan.quanlykaraoke.dto.NhapHangDTO;
import levantuan.quanlykaraoke.entities.ChiTietPhieuNhapHang;
import levantuan.quanlykaraoke.entities.DichVu;
import levantuan.quanlykaraoke.entities.PhieuNhapHang;
import levantuan.quanlykaraoke.repositories.*;
import levantuan.quanlykaraoke.service.NhapHangSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NhapHangSeriveImpl implements NhapHangSerive {

    @Autowired
    private PhieuNhapHangRepository phieuNhapHangRepository;

    @Autowired
    private ChiTietPhieuNhapHangRepository chiTietPhieuNhapHangRepository;

    @Autowired
    private DichVuRepository dichVuRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Override
    public PhieuNhapHang nhapHang(NhapHangDTO nhapHangDTOS, String username) {
        PhieuNhapHang phieuNhapHang = new PhieuNhapHang();
        Date date = new Date();
        phieuNhapHang.setThoiGianNhap(date);
        phieuNhapHang.setNhanVien(nhanVienRepository.findByUsername(username));
        phieuNhapHang.setDonViDoiTac(nhapHangDTOS.getDonViDoiTac());
        phieuNhapHang.setDiaChiDoiTac(nhapHangDTOS.getDiaChiDoiTac());
        phieuNhapHang.setNguoiDaiDien(nhapHangDTOS.getNguoiDaiDien());
        phieuNhapHang.setThongTinNDD(nhapHangDTOS.getThongTinNDD());
        //ma hoa don mac dinh HDZ user ngay  thang  nam  gio  phut giay
        phieuNhapHang.setMaPhieuNhap(username + new SimpleDateFormat("ddMMYYYYHHmmss").format(date));
        phieuNhapHang = phieuNhapHangRepository.save(phieuNhapHang);
        List<ChiTietPhieuNhapHang> chiTietPhieuNhapHangs = new ArrayList<>();
        for (DichVuDTO dichVu : nhapHangDTOS.getDichVus()) {
            ChiTietPhieuNhapHang chiTietPhieuNhapHang = new ChiTietPhieuNhapHang();
            DichVu dv = dichVuRepository.getOne(dichVu.getDichVuId());
            chiTietPhieuNhapHang.setDichVu(dv);
            dv.setSoLuongCon(dv.getSoLuongCon() + dichVu.getSl());
            dichVuRepository.save(dv);
            chiTietPhieuNhapHang.setSoLuong(dichVu.getSl());
            chiTietPhieuNhapHang.setDonGia(dichVu.getDonGia());
            chiTietPhieuNhapHang.setPhieuNhapHang(phieuNhapHang);
            chiTietPhieuNhapHangs.add(chiTietPhieuNhapHang);
        }
        chiTietPhieuNhapHangRepository.saveAll(chiTietPhieuNhapHangs);

        return phieuNhapHang;
    }

    @Override
    public PhieuNhapHang getById(Long id) {
        return phieuNhapHangRepository.getOne(id);
    }

    @Override
    public List<ChiTietPhieuNhapHang> getChiTiet(Long id) {
        PhieuNhapHang phieuNhapHang = phieuNhapHangRepository.getOne(id);
        return chiTietPhieuNhapHangRepository.findAllByPhieuNhapHang(phieuNhapHang);
    }
}
