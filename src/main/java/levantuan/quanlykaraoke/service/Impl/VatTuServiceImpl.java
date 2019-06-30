package levantuan.quanlykaraoke.service.Impl;

import levantuan.quanlykaraoke.entities.Phong;
import levantuan.quanlykaraoke.entities.VatTu;
import levantuan.quanlykaraoke.repositories.VatTuRepository;
import levantuan.quanlykaraoke.service.VatTuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VatTuServiceImpl implements VatTuService {

    @Autowired
    private VatTuRepository vatTuRepository;

    @Override
    public Page<VatTu> getAllVatTu(Integer page, Integer size) {
        return vatTuRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public VatTu updateVatTu(Long id, String ten) {
        VatTu vt = vatTuRepository.getOne(id);
        vt.setTenVatTu(ten);
        return vatTuRepository.save(vt);
    }

    @Override
    public VatTu newVatTu(String vatTu) {
        String lastMaPhong = vatTuRepository.getLastMaPhong();
        Integer maVT = 1;
        //format MVT0x
        if (lastMaPhong != null) {
            maVT = Integer.valueOf(lastMaPhong.substring(4));
            maVT++;
        }
        VatTu vt = new VatTu();
        vt.setTenVatTu((vatTu));
        vt.setMaVatTu("MVT0" + maVT);
        vt.setSoLuongVatTuCon(0);
        return vatTuRepository.save(vt);
    }

    @Override
    public boolean xoaVatTu(Long id) {
        Optional<VatTu> vatTu = vatTuRepository.findById(id);
        if (!vatTu.isPresent()) return false;
        if (vatTu.get().getSoLuongVatTuCon() > 0) return false;
        try {
            vatTuRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<VatTu> getAllVatTuByIdPhong(Long idPhong) {
        return vatTuRepository.getAllByIdPhong(idPhong);
    }
}
