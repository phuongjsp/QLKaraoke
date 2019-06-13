package levantuan.quanlykaraoke.service.Impl;

import levantuan.quanlykaraoke.entities.DichVu;
import levantuan.quanlykaraoke.repositories.DichVuRepository;
import levantuan.quanlykaraoke.service.DichVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DichVuServiceImpl implements DichVuService {

    @Autowired
    private DichVuRepository dichVuRepository;

    @Override
    public List<DichVu> getAllDichVu() {
        return dichVuRepository.findAll();
    }

    @Override
    public DichVu updateDichVu(DichVu dichVu) {

        DichVu dv = dichVuRepository.getOne(dichVu.getId());
        dv.setTenDichVu(dichVu.getTenDichVu());
        dv.setGia(dichVu.getGia());
        dv.setDonViDo(dichVu.getDonViDo());
        dichVuRepository.save(dichVu);
        return dichVu;

    }

    @Override
    public DichVu newDichVu(DichVu dichVu) {
        DichVu dv = new DichVu();
        dv.setTenDichVu(dichVu.getTenDichVu());
        dv.setGia(dichVu.getGia());
        dv.setDonViDo(dichVu.getDonViDo());
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

}
