package levantuan.quanlykaraoke.service.Impl;

import levantuan.quanlykaraoke.dto.PhongDTO;
import levantuan.quanlykaraoke.dto.UpdatePhongDTO;
import levantuan.quanlykaraoke.entities.ChiTietVatTu;
import levantuan.quanlykaraoke.entities.LoaiPhong;
import levantuan.quanlykaraoke.entities.Phong;
import levantuan.quanlykaraoke.entities.VatTu;
import levantuan.quanlykaraoke.repositories.ChiTietVatTuRepository;
import levantuan.quanlykaraoke.repositories.LoaiPhongRepository;
import levantuan.quanlykaraoke.repositories.PhongRepository;
import levantuan.quanlykaraoke.repositories.VatTuRepository;
import levantuan.quanlykaraoke.service.PhongHatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PhongHatServiceImpl implements PhongHatService {

    @Autowired
    private LoaiPhongRepository loaiPhongRepository;

    @Autowired
    private ChiTietVatTuRepository chiTietVatTuRepository;

    @Autowired
    private VatTuRepository vatTuRepository;

    @Autowired
    private PhongRepository phongRepository;
    @Override
    public List<LoaiPhong> getAllLoaiPhong() {
        return loaiPhongRepository.findAll();
    }

    @Override
    public LoaiPhong updateLoaiPhong(LoaiPhong loaiPhong) {
        LoaiPhong lp = loaiPhongRepository.getOne(loaiPhong.getId());
        lp.setLoaiPhong(loaiPhong.getLoaiPhong());
        lp.setTienPhongTrenGio(loaiPhong.getTienPhongTrenGio());
        loaiPhongRepository.save(loaiPhong);
        return loaiPhong;
    }

    @Override
    public LoaiPhong newLoaiPhong(LoaiPhong loaiPhong) {
        LoaiPhong lp = new LoaiPhong();
        lp.setLoaiPhong(loaiPhong.getLoaiPhong());
        lp.setTienPhongTrenGio(loaiPhong.getTienPhongTrenGio());
        return loaiPhongRepository.save(lp);
    }

    @Override
    public boolean xoaLoaiPhong(Long id) {
        try {
            loaiPhongRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Page<Phong> getALlPhong(Integer vip, Integer status, Integer page, Integer size) {
        if (vip == 0 && status == 0) {
            return phongRepository.findAll(PageRequest.of(page, size));
        } else if (vip == 0 && status != 0) {
            return phongRepository.findAllByTinhTrangPhong(status, PageRequest.of(page, size));
        } else if ( status == 0 && vip != 0) {
            return phongRepository.findAllByLoaiPhong(loaiPhongRepository.getOne(Long.valueOf(vip)), PageRequest.of(page, size));
        } else {
            return phongRepository.findAllByIdAndStatusLoaiPhong(vip, status, PageRequest.of(page, size));
        }
    }

    @Override
    public PhongDTO getById(Long idPhong) {
        PhongDTO phongDTO = new PhongDTO(phongRepository.getOne(idPhong));
        phongDTO.setVatTus(new ArrayList<>(chiTietVatTuRepository.findAllChiTietVatTuByPhongId(idPhong)));
        return phongDTO;
    }

    @Override
    public PhongDTO newPhong(PhongDTO phong) {
        String lastMaPhong = phongRepository.getLastMaPhong();
        Integer maP = 1;
        //format MP0x
        if (lastMaPhong != null) {
            maP = Integer.valueOf(lastMaPhong.substring(2));
            maP++;
        }
        Phong p = new Phong();
        p.setMaPhong("MP0" + maP);
        p.setTinhTrangPhong(1);
        p.setLoaiPhong(loaiPhongRepository.getOne(phong.getIdLoaiPhong()));
        phongRepository.save(p);
        phong.getUpdatePhongDTOS().forEach(dto -> chiTietVatTuRepository.insertNewVatTu(p.getId(), dto.getVattu().getId(), dto.getSl()));
        phong.setPhong(p);
        return phong;
    }

    @Override
    public boolean updatePhong(List<UpdatePhongDTO> phong, Long type, Long idPhong) {
        phongRepository.updateType(type, idPhong);
        List<ChiTietVatTu> vatTus = chiTietVatTuRepository.findAllChiTietVatTuByPhongId(idPhong);
        try {

            vatTus.forEach(chiTietVatTu -> phong.forEach(dto -> {
                long idVatTu = dto.getVattu().getId();
                if (chiTietVatTu.getVatTu().getId() == idVatTu) {
                    VatTu vatTu = vatTuRepository.getOne(dto.getVattu().getId());
                    Integer slvtInP = chiTietVatTuRepository.slVatTuInPhong(idPhong, idVatTu);
                    if (dto.getSl() > slvtInP) {
                        vatTu.setSoLuongVatTuCon(vatTu.getSoLuongVatTuCon() - (dto.getSl() - slvtInP));
                    } else {
                        vatTu.setSoLuongVatTuCon(vatTu.getSoLuongVatTuCon() + (slvtInP - dto.getSl()));
                    }
                    chiTietVatTuRepository.updateSlVatTuInPhong(dto.getSl(), chiTietVatTu.getId());
                }
            }));
            List<Long> listVatTuDelete = vatTus.stream().map(chiTietVatTu -> chiTietVatTu.getVatTu().getId()).collect(Collectors.toList());
            listVatTuDelete.removeAll(phong.stream().map(dto -> dto.getVattu().getId()).collect(Collectors.toList()));
            listVatTuDelete.forEach(aLong -> chiTietVatTuRepository.deleteByIdPhongAndIdVatTu(idPhong, aLong));

            List<Long> listIdVatTuNew = phong.stream().map(dto -> dto.getVattu().getId()).collect(Collectors.toList());
            listIdVatTuNew.removeAll(vatTus.stream().map(chiTietVatTu -> chiTietVatTu.getVatTu().getId()).collect(Collectors.toList()));
            listIdVatTuNew.forEach(aLong -> phong.forEach(dto -> {
                if (aLong == dto.getVattu().getId()) {
                    chiTietVatTuRepository.insertNewVatTu(idPhong, aLong, dto.getSl());
                }
            }));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


    @Override
    public boolean deletePhong(Long idPhong) {
         Integer xxx = phongRepository.deletePhongById(idPhong);
        System.out.println(xxx);
        return xxx == 0;
    }
}
