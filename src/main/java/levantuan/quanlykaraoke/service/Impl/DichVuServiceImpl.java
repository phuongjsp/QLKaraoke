package levantuan.quanlykaraoke.service.Impl;

import levantuan.quanlykaraoke.entities.DichVu;
import levantuan.quanlykaraoke.entities.PhieuNhapHang;
import levantuan.quanlykaraoke.repositories.DichVuRepository;
import levantuan.quanlykaraoke.repositories.NhanVienRepository;
import levantuan.quanlykaraoke.repositories.PhieuNhapHangRepository;
import levantuan.quanlykaraoke.service.DichVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class DichVuServiceImpl implements DichVuService {
    @Autowired
    private DichVuRepository dichVuRepository;

    @Autowired
    PhieuNhapHangRepository phieuNhapHangRepository;

    @Autowired
    NhanVienRepository nhanVienRepository;
    @Override
    public Page<DichVu> getAllDichVu(Integer page, Integer size) {
        return dichVuRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public DichVu updateDichVu(DichVu dichVu) {
        DichVu dv = dichVuRepository.getOne(dichVu.getId());
        return saveDichVu(dv, dichVu);
    }
    private DichVu saveDichVu(DichVu dv,DichVu dichVu) {
        dv.setTenDichVu((dichVu.getTenDichVu()));
        dv.setDonViDo(dichVu.getDonViDo());
        dv.setGia(dichVu.getGia());
        return dichVuRepository.save(dv);
    }

    @Override
    public DichVu newDichVu(DichVu dichVu) {
        DichVu dv = new DichVu();
        dv.setTenDichVu((dichVu.getTenDichVu()));
        dv.setDonViDo(dichVu.getDonViDo());
        dv.setGia(dichVu.getGia());
        dichVuRepository.save(dv);
        return dichVuRepository.save(dv);
    }

    @Override
    public boolean xoaDichVu(Long id) {
        try {
            dichVuRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<DichVu> danhSachDichVuNho() {
        return dichVuRepository.findAllDichVuSlNho();
    }

    @Override
    public Page<PhieuNhapHang> getAllPhieuNhapHang(String fromDate, String toDate, String nhanVien, Integer type, int page, Integer pageSize) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        boolean isFromDate = fromDate != null && !fromDate.equals("");
        boolean isToDate = toDate != null && !toDate.equals("");
        boolean isNhanVien = nhanVien != null && !nhanVien.equals("");
        try {
            if (isFromDate && isToDate && isNhanVien) {
                return phieuNhapHangRepository.findAllByThoiGianNhapLessThanEqualAndThoiGianNhapGreaterThanEqualAndNhanVienAndType(sdf.parse(toDate),
                        sdf.parse(fromDate), nhanVienRepository.findByUsername(nhanVien), type, PageRequest.of(page, pageSize));
            }
            if (isFromDate && isToDate) {
                return phieuNhapHangRepository.findAllByThoiGianNhapLessThanEqualAndThoiGianNhapGreaterThanEqualAndType(sdf.parse(toDate),
                        sdf.parse(fromDate), type, PageRequest.of(page, pageSize));
            }
            if (isToDate && isNhanVien) {
                return phieuNhapHangRepository.findAllByThoiGianNhapLessThanEqualAndNhanVienAndType(sdf.parse(toDate), nhanVienRepository.findByUsername(nhanVien), type, PageRequest.of(page, pageSize));
            }
            if (isToDate) {
                return phieuNhapHangRepository.findAllByThoiGianNhapLessThanEqualAndType(sdf.parse(toDate), type, PageRequest.of(page, pageSize));
            }
            if (isFromDate && isNhanVien) {
                return phieuNhapHangRepository.findAllByThoiGianNhapGreaterThanEqualAndNhanVienAndType(sdf.parse(fromDate), nhanVienRepository.findByUsername(nhanVien), type, PageRequest.of(page, pageSize));
            }
            if (isFromDate) {
                return phieuNhapHangRepository.findAllByThoiGianNhapGreaterThanEqualAndType(sdf.parse(fromDate), type, PageRequest.of(page, pageSize));
            }
            if (isNhanVien) {
                return phieuNhapHangRepository.findAllByNhanVienAndType(nhanVienRepository.findByUsername(nhanVien), type, PageRequest.of(page, pageSize));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return phieuNhapHangRepository.findAllByType(type, PageRequest.of(page, pageSize));
    }

}
