package levantuan.quanlykaraoke.service.Impl;

import levantuan.quanlykaraoke.entities.Vip;
import levantuan.quanlykaraoke.repositories.VipRepository;
import levantuan.quanlykaraoke.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VipImpl implements VipService {
    @Autowired
    private VipRepository vipRepository;

    @Override
    public List<Vip> getAllVip() {
        return vipRepository.findAll();
    }

    @Override
    public Vip updateVip(Vip vip) {
        Vip vp = vipRepository.getOne(vip.getId());
        return saveVip(vp, vip);

    }
    private Vip saveVip(Vip vp,Vip vip) {
        vp.setTenLoaiVip(vip.getTenLoaiVip());
        vp.setMucTichLuy(vip.getHoaDonGiam());
        vp.setMucTichLuy(vip.getMucTichLuy());
        return vipRepository.save(vip);
    }
    @Override
    public Vip newVip(Vip vip) {
        Vip vp = new Vip();
        return saveVip(vp, vip);
    }

    @Override
    public boolean xoaVip(Long id) {
        try {
            vipRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
