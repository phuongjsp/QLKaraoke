package levantuan.quanlykaraoke.service;

import levantuan.quanlykaraoke.entities.DichVu;

import java.util.List;
public interface DichVuService {
    List<DichVu> getAllDichVu();

    DichVu updateDichVu(DichVu dichVu);

    DichVu newDichVu(DichVu dichVu);

    boolean xoaDichVu(Long id);
}

