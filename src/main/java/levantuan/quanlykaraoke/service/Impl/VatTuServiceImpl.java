package levantuan.quanlykaraoke.service.Impl;

import levantuan.quanlykaraoke.entities.VatTu;
import levantuan.quanlykaraoke.repositories.VatTuRepository;
import levantuan.quanlykaraoke.service.VatTuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VatTuServiceImpl implements VatTuService {

    @Autowired
    private VatTuRepository vatTuRepository;

    @Override
    public Page<VatTu> getAllVatTu(Integer page, Integer size) {
        return vatTuRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public VatTu updateVatTu(VatTu vatTu) {

        VatTu vt = vatTuRepository.getOne(vatTu.getId());
        vt.setMaVatTu(vatTu.getMaVatTu());
        vt.setTenVatTu(vatTu.getTenVatTu());
        vt.setSoLuongVatTuCon(vatTu.getSoLuongVatTuCon());
        vatTuRepository.save(vatTu);
        return vatTu;

    }

    @Override
    public VatTu newVatTu(VatTu vatTu) {

        VatTu vt = new VatTu();
        vt.setTenVatTu((vatTu.getTenVatTu()));
        vt.setMaVatTu(vatTu.getTenVatTu());
        vt.setSoLuongVatTuCon(vatTu.getSoLuongVatTuCon());
        return vatTuRepository.save(vt);
    }

    @Override
    public boolean xoaVatTu(Long id) {
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
