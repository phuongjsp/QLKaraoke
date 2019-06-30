package levantuan.quanlykaraoke.service.Impl;

import levantuan.quanlykaraoke.dto.SanPhamDTO;
import levantuan.quanlykaraoke.dto.NhapHangDTO;
import levantuan.quanlykaraoke.entities.*;
import levantuan.quanlykaraoke.repositories.*;
import levantuan.quanlykaraoke.service.NhapHangSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NhapHangSeriveImpl implements NhapHangSerive {

    @Autowired
    private PhieuNhapHangRepository phieuNhapHangRepository;

    @Autowired
    private ChiTietPhieuNhapHangRepository chiTietPhieuNhapHangRepository;

    @Autowired
    private ChiTietPhieuNhapVatTuRepository chiTietPhieuNhapVatTuRepository;

    @Autowired
    private DichVuRepository dichVuRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private VatTuRepository vatTuRepository;

    @Override
    public Long nhapHang(NhapHangDTO nhapHangDTOS, String username) {
        PhieuNhapHang phieuNhapHang = new PhieuNhapHang();
        Date date = new Date();
        phieuNhapHang.setThoiGianNhap(date);
        phieuNhapHang.setNhanVien(nhanVienRepository.findByUsername(username));
        phieuNhapHang.setDonViDoiTac(nhapHangDTOS.getDonViDoiTac());
        phieuNhapHang.setDiaChiDoiTac(nhapHangDTOS.getDiaChiDoiTac());
        phieuNhapHang.setNguoiDaiDien(nhapHangDTOS.getNguoiDaiDien());
        phieuNhapHang.setThongTinNDD(nhapHangDTOS.getThongTinNDD());
        //ma hoa don mac dinh HDZ user ngay  thang  nam  gio  phut giay
        phieuNhapHang.setMaPhieuNhap("PNHDV" + username + new SimpleDateFormat("ddMMyyyyHHmmss").format(date));
        phieuNhapHang.setType(0);
        phieuNhapHang = phieuNhapHangRepository.save(phieuNhapHang);
        Integer tongTien = 0;
        List<ChiTietPhieuNhapHang> chiTietPhieuNhapHangs = new ArrayList<>();
        for (SanPhamDTO dichVu : nhapHangDTOS.getSanPhamDTOS()) {
            ChiTietPhieuNhapHang chiTietPhieuNhapHang = new ChiTietPhieuNhapHang();
            Optional<DichVu> dv = dichVuRepository.findById(dichVu.getSanPhamId());
            if (dv.isPresent()) {
                chiTietPhieuNhapHang.setDichVu(dv.get());
                dv.get().setSoLuongCon(dv.get().getSoLuongCon() + dichVu.getSl());
                dichVuRepository.save(dv.get());
                chiTietPhieuNhapHang.setSoLuong(dichVu.getSl());
                chiTietPhieuNhapHang.setDonGia(dichVu.getDonGia());
                chiTietPhieuNhapHang.setPhieuNhapHang(phieuNhapHang);
                chiTietPhieuNhapHangs.add(chiTietPhieuNhapHang);
                tongTien += chiTietPhieuNhapHang.getDonGia() * chiTietPhieuNhapHang.getSoLuong();
            }
        }
        chiTietPhieuNhapHangRepository.saveAll(chiTietPhieuNhapHangs);
        phieuNhapHang.setTongTien(tongTien);
        return phieuNhapHangRepository.save(phieuNhapHang).getId();
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

    @Override
    public List<ChiTietPhieuNhapVatTu> getChiTietVatTu(Long id) {
         PhieuNhapHang phieuNhapHang = phieuNhapHangRepository.getOne(id);
        return chiTietPhieuNhapVatTuRepository.findAllByPhieuNhapHang(phieuNhapHang);
    }

    @Override
    public Long nhapHangVatTu(NhapHangDTO nhapHangDTOS, String username) {
        PhieuNhapHang phieuNhapHang = new PhieuNhapHang();
        Date date = new Date();
        phieuNhapHang.setThoiGianNhap(date);
        phieuNhapHang.setNhanVien(nhanVienRepository.findByUsername(username));
        phieuNhapHang.setDonViDoiTac(nhapHangDTOS.getDonViDoiTac());
        phieuNhapHang.setDiaChiDoiTac(nhapHangDTOS.getDiaChiDoiTac());
        phieuNhapHang.setNguoiDaiDien(nhapHangDTOS.getNguoiDaiDien());
        phieuNhapHang.setThongTinNDD(nhapHangDTOS.getThongTinNDD());
        //ma hoa don mac dinh HDZ user ngay  thang  nam  gio  phut giay
        phieuNhapHang.setMaPhieuNhap("PNHVT" + username + new SimpleDateFormat("ddMMyyyyHHmmss").format(date));
        phieuNhapHang.setType(1);
        phieuNhapHang = phieuNhapHangRepository.save(phieuNhapHang);
        Integer tongTien = 0;
        List<ChiTietPhieuNhapVatTu> chiTietPhieuNhapVatTus = new ArrayList<>();
        for (SanPhamDTO sanPhamDTO : nhapHangDTOS.getSanPhamDTOS()) {
            ChiTietPhieuNhapVatTu chiTietPhieuNhapVatTu = new ChiTietPhieuNhapVatTu();
            Optional<VatTu> vatTu = vatTuRepository.findById(sanPhamDTO.getSanPhamId());
            if (vatTu.isPresent()) {
                chiTietPhieuNhapVatTu.setVatTu(vatTu.get());
                vatTu.get().setSoLuongVatTuCon(vatTu.get().getSoLuongVatTuCon() + sanPhamDTO.getSl());
                vatTuRepository.save(vatTu.get());
                chiTietPhieuNhapVatTu.setSoLuong(sanPhamDTO.getSl());
                chiTietPhieuNhapVatTu.setDonGia(sanPhamDTO.getDonGia());
                chiTietPhieuNhapVatTu.setPhieuNhapHang(phieuNhapHang);
                chiTietPhieuNhapVatTus.add(chiTietPhieuNhapVatTu);
                tongTien += chiTietPhieuNhapVatTu.getDonGia() * chiTietPhieuNhapVatTu.getSoLuong();
            }
        }
        chiTietPhieuNhapVatTuRepository.saveAll(chiTietPhieuNhapVatTus);
        phieuNhapHang.setTongTien(tongTien);
        return phieuNhapHangRepository.save(phieuNhapHang).getId();
    }
}
